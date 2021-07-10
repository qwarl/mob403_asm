package com.kietlpt.mob402_socket.DAO;

import android.content.Context;
import android.util.Log;

import com.kiennv.androidbaove.Model.Sach;
import com.kiennv.androidbaove.noUI;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SachDao {
    Context context;

    String serverUrl = "http://10.82.146.3:3000";
    List<Sach> list = new ArrayList<Sach>();

    //Khai bao socket
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(serverUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    //lang nghe insert
    private Emitter.Listener onInsertSach = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String data =  args[0].toString();

            noUI noui = new noUI(context);

            if(data == "true"){
                Log.i("insert","Them sach thanh cong");
                noui.toast("Them sach thanh cong");


            }else{
                Log.i("insert","Them sach that bai");
                noui.toast("Them sach that bai");
            }

        }
    };

    public SachDao(Context context) {
        this.context = context;
        mSocket.connect();
        mSocket.on("insertSach", onInsertSach);
    }


    public void insert(final Sach sv) {
        mSocket.emit("insertSach", sv.name, sv.tacgia, sv.theloai, sv.nxb, sv.gia);
    }





}
