package fr.aureliejosephine.mareu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.aureliejosephine.mareu.DI.DI;
import fr.aureliejosephine.mareu.modele.Reunion;
import androidx.recyclerview.widget.RecyclerView;
import fr.aureliejosephine.mareu.services.ReunionService;
import java.util.ArrayList;
import java.util.List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ReunionRecyclerViewAdapter extends RecyclerView.Adapter<ReunionRecyclerViewAdapter.ViewHolder> implements Filterable {

    private List<Reunion> list;
    private List<Reunion> listFull;
    private LayoutInflater mInflater;
    private ReunionService mReunionService;
    private Context context;


    public ReunionRecyclerViewAdapter(Context context, List<Reunion> reunions ) {
        this.mInflater = LayoutInflater.from(context);
        this.list = reunions;
        listFull = new ArrayList<>(reunions);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reunion, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mReunionService = DI.getReunionService();
        final Reunion reunion = list.get(position);

        holder.mLieu.setText(reunion.getLieu());
        holder.mSujet.setText(reunion.getSujet());
        holder.mEmail.setText(reunion.getAddEmail().toString().replace("[", " ").replace("]", " ").replace(",", ""));
        holder.mHeure.setText(reunion.getHeure());
        holder.mDate.setText(reunion.getDate());

        Glide.with(holder.mImage.getContext())
                .load(reunion.getAvatar())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mImage);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(context.getString(R.string.confirm_delete));
                builder.setCancelable(false);
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mReunionService.deleteReunion(reunion);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, context.getString(R.string.toast_delete), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                //notifyItemChanged(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Reunion> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Reunion item : listFull) {
                    if ((item.getLieu().toLowerCase().contains(filterPattern)) || (item.getDate().toLowerCase().contains(filterPattern))) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_reunion)
       public ImageView mImage;
        @BindView(R.id.salleText)
       public TextView mLieu;
        @BindView(R.id.sujetText)
       public TextView mSujet;
        @BindView(R.id.emailText)
       public TextView mEmail;
        @BindView(R.id.heureText)
       public TextView mHeure;
        @BindView(R.id.dateText)
       public TextView mDate;
        @BindView(R.id.deleteButton)
       public ImageButton mDeleteButton;
       public View layout;

        public ViewHolder(View view) {
            super(view);
            layout = view;

            ButterKnife.bind(this, view);
        }
    }
}
