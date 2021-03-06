package fr.aureliejosephine.mareu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Meeting;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.aureliejosephine.mareu.services.MeetingService;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.ViewHolder> /*implements Filterable*/ {

    private List<Meeting> list;
    private MeetingService mMeetingService;
    private Context context;


    MeetingRecyclerViewAdapter(List<Meeting> meetings) {
        this.list = meetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mMeetingService = DI.getMeetingService();
        final Meeting meeting = list.get(position);

        String text = context.getString(R.string.title, meeting.getRoom(), meeting.getHour(), meeting.getSubject());
        holder.roomHourSubjTv.setText(text);
        holder.mEmail.setText(meeting.getEmailList().toString().replace("[", " ").replace("]", " "));

        Glide.with(holder.mImage.getContext())
                .load(meeting.getAvatar())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mImage);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(context.getString(R.string.confirm_delete));
                builder.setCancelable(false);
                builder.setPositiveButton(context.getString(R.string.oui), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mMeetingService.deleteMeeting(meeting);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, context.getString(R.string.toast_delete), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton(context.getString(R.string.non), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.roomHourSubjTv)
        public TextView roomHourSubjTv;
        @BindView(R.id.image_reunion)
        public ImageView mImage;
        @BindView(R.id.emailText)
        public TextView mEmail;
        @BindView(R.id.deleteButton)
        public ImageButton mDeleteButton;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
