package myapps.wycoco.com.ethelonstartup.Models;

import com.squareup.otto.Bus;

/**
 * Created by dell on 8/21/2017.
 */

public class BusStation {
    private static Bus bus = new Bus();

    public static Bus getBus(){
        return bus;
    }
}
