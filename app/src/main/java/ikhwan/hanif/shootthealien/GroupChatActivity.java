package ikhwan.hanif.shootthealien;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GroupChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editTextMessage;
    private ImageView buttonSend;
    private TextView belumAdaPesan;

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    private String groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        groupId = getIntent().getStringExtra("groupId");
        if (groupId == null) {
            Toast.makeText(this, "Group ID is missing", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerViewMessages);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);
        belumAdaPesan = findViewById(R.id.belumadapesan);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList,auth);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        listenForMessages();
    }

    private void sendMessage() {
        String content = editTextMessage.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }


        String senderId = auth.getCurrentUser().getUid();
        String senderName = auth.getCurrentUser().getDisplayName(); // Ensure the display name is set
        String timestamp = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        Map<String, Object> message = new HashMap<>();
        message.put("senderId", senderId);
        message.put("senderName", senderName);
        message.put("content", content);
        message.put("timestamp", timestamp);

        db.collection("groups").document(groupId)
                .collection("messages")
                .add(message)
                .addOnSuccessListener(doc -> {
                    editTextMessage.setText("");
                    Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show(); // Confirm message sent
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show();
                });
    }

    private void listenForMessages() {
        db.collection("groups").document(groupId)
                .collection("messages")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Toast.makeText(this, "Failed to load messages", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    messageList.clear(); // Clear the message list before adding new messages

                    for (DocumentSnapshot doc : value.getDocuments()) {
                        Message message = doc.toObject(Message.class);
                        if (message != null) {
                            belumAdaPesan.setVisibility(View.GONE);
                            message.setMessageId(doc.getId()); // Set the document ID as the message ID
                            messageList.add(message);
                        }
                    }


                    messageAdapter.notifyDataSetChanged(); // Notify the adapter that data has changed
                    if (!messageList.isEmpty()) {
                        recyclerView.smoothScrollToPosition(messageList.size() - 1); // Scroll to the last message
                    }
                });
    }
}
