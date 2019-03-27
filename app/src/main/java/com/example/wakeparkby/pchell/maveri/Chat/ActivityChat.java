package com.example.wakeparkby.pchell.maveri.Chat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.wakeparkby.pchell.maveri.ObserverMessage;
import com.example.wakeparkby.pchell.maveri.Profile.ActivityProfile;
import com.example.wakeparkby.pchell.maveri.Profile.Profile;
import com.example.wakeparkby.pchell.maveri.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ActivityChat extends AppCompatActivity implements View.OnClickListener {
    AdapterChat adapterChat = Profile.getInstance().getAdapterChat();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRefMeessage;
    private ImageView sendButton;
    private ImageView selectPlaceButton;
    private EditText messageArea;
    private ListView listViewChat;
    private String groupId;
    private String userKey = "userKey";
    private String a;
    private List<String> chatList;// = adapterChat.getListMessage().getMessages();
    private final int IDD_THREE_BUTTONS = 0;
    private LatLng latLng = new LatLng(18.5259949,109.3576236);
    //AdapterChat adapterChat = new AdapterChat();


    ObserverMessage observer = new ObserverMessage("Chat") {

        /**
         * override method of Observer class with new reaction for notify observers

         */

        @Override
        public void update() {
            int n = observer.getStatus();

            if (n == 10) {

                if (observer.getId() == 1) {
                    refreshList();
                    observer.setId(0);
                } else {
                }
            }
        }


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listViewChat = findViewById(R.id.listViewChat);
        sendButton = findViewById(R.id.sendButton);
        messageArea = findViewById(R.id.messageArea);
        sendButton.setOnClickListener(this);
        // adapterChat.getListMessage(profile.getUserKey(),);
        //groupId = adapterChat.getGroupId();
        selectPlaceButton = findViewById(R.id.placeButton);
        selectPlaceButton.setOnClickListener(this);
        refreshList();


    }


    public void refreshList() {
        chatList = Profile.getInstance().getAdapterChat().getListMessage().getMessages();
        ArrayAdapter<String> chatAdapter = new ArrayAdapter<>(ActivityChat.this,
                android.R.layout.simple_list_item_1,
                chatList.toArray(new String[chatList.size()]));
        listViewChat.setAdapter(chatAdapter);
        //ListMessage listMessage = new ListMessage();
        //listMessage.setChatList(chatList);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sendButton: {
                String messageText = messageArea.getText().toString();
                adapterChat.sendMessage(messageText);
                messageArea.setText("");
                break;
            }

            case R.id.placeButton: {
                //AdapterChat.startActivityLocationSelection(this);
                showDialog(IDD_THREE_BUTTONS);
                break;
            }
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case IDD_THREE_BUTTONS:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Profile.getInstance().getFirstName() + "предложил встречу");
                builder.setMessage("Дата: " + "28.03.2019" + System.lineSeparator() + "Время: " + "18:30" + System.lineSeparator() + "Место: " + "Бар <<Веселый пчел>>"
                        + System.lineSeparator() + "Адрес: " + "ул. Святого Пчела 8")
                        .setCancelable(false)
                        .setPositiveButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Место на карте",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        startActivityMaps();
                                    }
                                })
                        .setNeutralButton("Согласиться",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });

                return builder.create();
            default:
                return null;
        }
    }

    private void startActivityMaps() {
        AdapterChat.startActivityLocationSelection(this,latLng);
    }


}
