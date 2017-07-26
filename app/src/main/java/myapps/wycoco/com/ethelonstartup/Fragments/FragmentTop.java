//package myapps.wycoco.com.ethelonstartup.Fragments;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import myapps.wycoco.com.ethelonstartup.Libraries.Travel;
//import myapps.wycoco.com.ethelonstartup.Models.ActivityModel;
//import myapps.wycoco.com.ethelonstartup.R;
//
//
///**
// * Created by Acer on 04/12/2016.
// */
//
//public class FragmentTop extends Fragment {
//
//    static final String ARG_TRAVEL = "ARG_TRAVEL";
//    Travel travel;
//    ActivityModel activityModel;
//
//    @Bind(R.id.image)
//    ImageView image;
//    @Bind(R.id.title)
//    TextView title;
//
//    public static FragmentTop newInstance(Travel travel) {
//        Bundle args = new Bundle();
//        FragmentTop fragmentTop = new FragmentTop();
//        args.putParcelable(ARG_TRAVEL, travel);
//        fragmentTop.setArguments(args);
//        return fragmentTop;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle args = getArguments();
//        if (args != null) {
//            travel = args.getParcelable(ARG_TRAVEL);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_front, container, false);
//    }
//
//    @Override
//    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        ButterKnife.bind(this, view);
//
//        if (travel != null) {
//            Glide.with(getActivity()).load(activityModel.getActivityImage()).into(image);
//            title.setText(travel.getName());
//          //  Log.e("Anton","Fragment Top "+travel.getName());
//        }
//
//    }
//}
