package myapps.wycoco.com.ethelonstartup.Fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluateFragment extends Fragment {


    public EvaluateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);

        String activity_id = getArguments().getString("activity_id");
        String api_token = getArguments().getString("api_token");
        String volunteer_id = getArguments().getString("volunteer_id");

        VolunteerRatingFragment volunteerRatingFragment = new VolunteerRatingFragment();
        CriteriaFragment criteriaFragment = new CriteriaFragment();

        Bundle bundle = new Bundle();
        bundle.putString("api_token", api_token);
        bundle.putString("activity_id", activity_id);
        bundle.putString("volunteer_id", volunteer_id);
        volunteerRatingFragment.setArguments(bundle);
        criteriaFragment.setArguments(bundle);

        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction()
                .replace(R.id.volunteerFrame, volunteerRatingFragment)
                .commit();

        return view;
    }

}
