package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;
import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/28/2017.
 */

public class EvaluationCriteriaAdapter extends RecyclerView.Adapter<EvaluationCriteriaAdapter.ViewHolder> {

    Context mContext;
    ArrayList<EvaluationCriteria> criteria = new ArrayList<>();
    private OnClickListener mLiistener;

    public EvaluationCriteriaAdapter(Context mContext, ArrayList<EvaluationCriteria> criteria, OnClickListener mLiistener) {
        this.mContext = mContext;
        this.criteria = criteria;
        this.mLiistener = mLiistener;
        Log.e("Kixbogo",criteria.size()+"");
    }

    public EvaluationCriteriaAdapter() {
    }

    @Override
    public EvaluationCriteriaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluate_criteria_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EvaluationCriteriaAdapter.ViewHolder holder, int position) {
        Log.e("line48",criteria.size()+" name = "+criteria.get(position).getCriteriaName());
        holder.criteriaName.setText(criteria.get(position).getCriteriaName());

    }

    @Override
    public int getItemCount() {
        return criteria.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView criteriaName;
        RatingBar criteriaRating;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            criteriaName = (TextView)itemView.findViewById(R.id.criteriaTxt);
            criteriaRating = (RatingBar)itemView.findViewById(R.id.criteriaRating);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linear);


            criteriaRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                    int rating = (int) v;
                    String criterion = criteria.get(getAdapterPosition()).getCriteriaName();

                    Bundle a = new Bundle();
                    a.putInt("rating", rating);
                    a.putString("criteria_name", criterion);
                    mLiistener.onClick(rating, getAdapterPosition());
                }
            });

        }
    }

    public interface OnClickListener{
        public void onClick(int rating, int index);

    }

}
