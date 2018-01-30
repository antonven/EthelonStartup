package myapps.wycoco.com.ethelonstartup.Adapters;

import java.util.ArrayList;
import java.util.List;

import myapps.wycoco.com.ethelonstartup.Models.SkillBadgesModel;
import myapps.wycoco.com.ethelonstartup.R;

/**
 * Created by dell on 1/17/2018.
 */

public class ProfileSkillsController {

    private List<SkillBadgesModel> mSkillBadges;

    public ProfileSkillsController(){

        mSkillBadges = new ArrayList<>();


//        mSkillBadges.add(new SkillBadgesModel("Artist Badge", R.drawable.arts_1, 70));
//
//        mSkillBadges.add(new SkillBadgesModel("Helper Badge", R.drawable.charity_legend, 80));
//
//        mSkillBadges.add(new SkillBadgesModel("Master Chef Badge", R.drawable.culinary_expert, 80));
//
//        mSkillBadges.add(new SkillBadgesModel("Professor Badge", R.drawable.education_expert, 80));
//
//        mSkillBadges.add(new SkillBadgesModel("Environmentalist Badge", R.drawable.environment_expert, 80));
//
//        mSkillBadges.add(new SkillBadgesModel("Master Carpenter Badge", R.drawable.livelihood_expert, 80));
//
//        mSkillBadges.add(new SkillBadgesModel("Surgeon Badge", R.drawable.medicine_expert, 80));
//
//        mSkillBadges.add(new SkillBadgesModel("Olympian Badge", R.drawable.sports_expert, 80));


    }

    public List<SkillBadgesModel> getmSkillBadges() { return mSkillBadges; }


}
