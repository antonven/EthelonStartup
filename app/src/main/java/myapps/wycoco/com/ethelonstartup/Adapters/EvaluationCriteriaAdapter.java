package myapps.wycoco.com.ethelonstartup.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

import myapps.wycoco.com.ethelonstartup.Fragments.DialogFragmentAttendanceSuccess;
import myapps.wycoco.com.ethelonstartup.Models.AdapterInterface;
import myapps.wycoco.com.ethelonstartup.Models.EvaluationCriteria;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 8/28/2017.
 */

public class EvaluationCriteriaAdapter extends RecyclerView.Adapter<EvaluationCriteriaAdapter.ViewHolder> {

    Context mContext;
    ArrayList<EvaluationCriteria> criteria = new ArrayList<>();
    ArrayList<Integer> rates = new ArrayList<>();
    AdapterInterface adapterInterface;

    public EvaluationCriteriaAdapter(Context mContext, ArrayList<EvaluationCriteria> criteria) {
        this.mContext = mContext;
        this.criteria = criteria;
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
        Button doneBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            criteriaName = (TextView)itemView.findViewById(R.id.criteriaTxt);
            criteriaRating = (RatingBar)itemView.findViewById(R.id.criteriaRating);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linear);


            criteriaRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//                    ratingBar.setRating(v);
                    int rating = (int) v;
                    String criterion = criteria.get(getAdapterPosition()).getCriteriaName();
//                    rates.add(rating);
                    Bundle a = new Bundle();
                    a.putInt("rating", rating);
                    a.putString("criteria_name", criterion);
                    DialogFragmentAttendanceSuccess fd = new DialogFragmentAttendanceSuccess();
                    fd.setArguments(a);
                    adapterInterface.onChanged(criterion);
//                    adapterInterface.ratingChanged("");
//                    criteria.get(getAdapterPosition()).setCriteriaRating(rating);
//                    evaluateGroupAdapter = new EvaluateGroupAdapter(mContext, criteria);
                }
            });

        }
    }

    public void setOnChangedRating(AdapterInterface adapterInterface){
        this.adapterInterface = adapterInterface;
    }




}
