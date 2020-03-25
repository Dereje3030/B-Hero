package com.ethioptech.b_hero.fragments;

import android.app.DownloadManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ethioptech.b_hero.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class RequestFragement extends Fragment {
    FirestoreRecyclerAdapter<MessageModel,ViewHolder> firestoreRecyclerAdapter;
    FirestoreRecyclerOptions<MessageModel> firestoreRecyclerOptions;
    FirebaseFirestore firebaseFirestore;
    Query query;
    private RecyclerView recyclerView;
    public RequestFragement() {

    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view= inflater.inflate(R.layout.fragment_request_fragement, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseFirestore=FirebaseFirestore.getInstance();
        query=firebaseFirestore.collection("request");
        firestoreRecyclerOptions=new FirestoreRecyclerOptions.Builder<MessageModel>().setQuery(query,MessageModel.class).build();
        firestoreRecyclerAdapter=new FirestoreRecyclerAdapter<MessageModel, ViewHolder>(firestoreRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MessageModel model) {
               Picasso.get().load(model.getImagePath()).into(holder.request_image);
                holder.request_Description.setText(model.getDescription());
                Toast.makeText(getContext(),model.getDescription(),Toast.LENGTH_SHORT);
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
                return new ViewHolder(view);
            }
        };
        recyclerView.setAdapter(firestoreRecyclerAdapter);
        return view;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView request_image;
        TextView request_Description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            request_image=itemView.findViewById(R.id.post_image);
            request_Description=itemView.findViewById(R.id.post_description);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        firestoreRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(firestoreRecyclerAdapter!=null){
            firestoreRecyclerAdapter.stopListening();
        }
    }
}