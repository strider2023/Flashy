package com.touchmenotapps.flashy.dao.interfaces;

import com.touchmenotapps.flashy.dao.enums.ServerEvents;

public interface ServerResponseListener {

    void onSuccess(int threadId, Object object);

    void onFaliure(ServerEvents serverEvents, Object object);
}
