package com.example.nav_test.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nav_test.R;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class team_recycler_view_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mData = null;

    int max;

    public String getmData(int pos) {
        return mData.get(pos);
    }







    public class ViewHolder0 extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        Button delete;

        ViewHolder0(View itemView){
            super(itemView);
            delete = itemView.findViewById(R.id.team_delete_button);
            title = itemView.findViewById(R.id.team_title);//수정 필요!
            image = itemView.findViewById(R.id.recycler_imageview);

            delete.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(bListener!=null){
                            bListener.onButtonClick(v,pos);
                        }
                    }
                    notifyDataSetChanged();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener!=null){
                            mListener.onItemClick(v,pos);
                        }
                    }
                    //notifyDataSetChanged();
                }
            });
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder{
        FrameLayout new_teamgrass;
        ViewHolder2(View itemView){
            super(itemView);

            new_teamgrass = itemView.findViewById(R.id.new_teamgrass);//수정 필요!

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener!=null){
                            mListener.onItemClick(v,pos);
                        }
                    }

                }
            });
        }
    }

    team_recycler_view_adapter(ArrayList<String> list){
        mData = list;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mData.size()-1){
            return 2;
        }
        else
            return 0;
    }





    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;
        team_recycler_view_adapter.ViewHolder0 vh0;
        team_recycler_view_adapter.ViewHolder2 vh2;

        switch(viewType){
            case 0:
                view = inflater.inflate(R.layout.teamgrass_recyclerview_item,parent,false);
                vh0 = new team_recycler_view_adapter.ViewHolder0(view);
                return vh0;
            case 2:
                view = inflater.inflate(R.layout.item_new_teamgrass,parent,false);
                vh2 = new team_recycler_view_adapter.ViewHolder2(view);
                return vh2;
        }


        view = inflater.inflate(R.layout.item_new_teamgrass,parent,false);
        vh2 = new team_recycler_view_adapter.ViewHolder2(view);
        return vh2;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 0:
                ViewHolder0 viewHolder0 = (ViewHolder0)holder;
                String teamname = mData.get(position);
                String txt_removed_teamname = teamname.substring(0, teamname.lastIndexOf("."));

                viewHolder0.title.setText(txt_removed_teamname);

                File file = new File("/data/data/com.example.nav_test/cache/"+txt_removed_teamname+".jpg" );

                Log.e("image path","/data/data/com.example.nav_test/cache/"+txt_removed_teamname+".jpg");
                if (file.exists()&&file != null) {
                    Bitmap bitmap = BitmapFactory.decodeFile("/data/data/com.example.nav_test/cache/"+txt_removed_teamname+".jpg");
                    viewHolder0.image.setImageBitmap(bitmap);
                }

                break;
            case 2:
                ViewHolder2 viewHolder2 = (ViewHolder2)holder;
                viewHolder2.new_teamgrass.setBackgroundResource(R.drawable.round_cornered_add_icon);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int pos);
    }

    public interface OnButtonClickListener{
        void onButtonClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    private OnButtonClickListener bListener = null;


    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public void setOnButtonClickListener(OnButtonClickListener listener){
        this.bListener = listener;
    }
}
