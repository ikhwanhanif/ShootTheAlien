package ikhwan.hanif.shootthealien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messageList;
    private FirebaseAuth auth;

    public MessageAdapter(List<Message> messageList, FirebaseAuth auth) {
        this.messageList = messageList;
        this.auth = auth;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messageList.get(position);
        // Return 0 for sent message and 1 for received message
        return message.getSenderId().equals(auth.getCurrentUser().getUid()) ? 0 : 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) { // Sent message
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageViewHolder(view);
        } else { // Received message
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (holder instanceof SentMessageViewHolder) {
            SentMessageViewHolder sentHolder = (SentMessageViewHolder) holder;
            sentHolder.senderNameTextView.setText(message.getSenderName()+" (YOU)");
            sentHolder.contentTextView.setText(message.getContent());
            sentHolder.timestampTextView.setText(message.getTimestamp());
        } else if (holder instanceof ReceivedMessageViewHolder) {
            ReceivedMessageViewHolder receivedHolder = (ReceivedMessageViewHolder) holder;
            receivedHolder.senderNameTextView.setText(message.getSenderName());
            receivedHolder.contentTextView.setText(message.getContent());
            receivedHolder.timestampTextView.setText(message.getTimestamp());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView senderNameTextView;
        TextView contentTextView;
        TextView timestampTextView;

        public SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            senderNameTextView = itemView.findViewById(R.id.textViewSenderName);
            contentTextView = itemView.findViewById(R.id.textViewContent);
            timestampTextView = itemView.findViewById(R.id.textViewTimestamp);
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        TextView senderNameTextView;
        TextView contentTextView;
        TextView timestampTextView;

        public ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            senderNameTextView = itemView.findViewById(R.id.textViewSenderName);
            contentTextView = itemView.findViewById(R.id.textViewContent);
            timestampTextView = itemView.findViewById(R.id.textViewTimestamp);
        }
    }
}