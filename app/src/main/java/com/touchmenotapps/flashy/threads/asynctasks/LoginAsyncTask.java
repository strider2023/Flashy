package com.touchmenotapps.flashy.threads.asynctasks;

import android.content.Context;

import com.touchmenotapps.flashy.dao.enums.ServerEvents;
import com.touchmenotapps.flashy.dao.interfaces.ServerResponseListener;

/**
 * Created by school on 23/8/16.
 */
public class LoginAsyncTask extends AppTask {

    public LoginAsyncTask(int id, Context context, ServerResponseListener serverResponseListener) {
        super(id, context, serverResponseListener);
    }

    @Override
    protected ServerEvents doInBackground(Object... objects) {
        return null;
    }

    @Override
    protected void onPostExecute(ServerEvents serverEvents) {
        super.onPostExecute(serverEvents);
    }
}
