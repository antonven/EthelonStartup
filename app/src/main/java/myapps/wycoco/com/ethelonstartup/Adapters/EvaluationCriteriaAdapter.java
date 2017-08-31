package myapps.wycoco.com.ethelonstartup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/28/2017.
 */

public class EvaluationCriteriaAdapter extends RecyclerView.Adapter<EvaluationCriteriaAdapter.ViewHolder> {

    Context mContext;
    ArrayList<EvaluationCriteria> criteria = new ArrayList<>();

    @Override
    public EvaluationCriteriaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evaluate_criteria_item, parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EvaluationCriteriaAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return criteria.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView criteria;
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            criteria = (TextView)itemView.findViewById(R.id.criteriaTxt);
            ratingBar = (RatingBar)itemView.findViewById(R.id.criteriaRating);
        }
    }
}