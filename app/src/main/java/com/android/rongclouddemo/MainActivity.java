package com.android.rongclouddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;

public class MainActivity extends AppCompatActivity {

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View v) {
        if (v.getId() == R.id.user_one) {
            token = "AGE0yacuUnFauaVIDU2n9i1pWs4wW9sEUof+zGDK7ogFB4repyPQPP4TPgZm2LXWbFhXgJSgtgI5UAY4nr3dgg==";
            connect(token);
        }
        if (v.getId() == R.id.user_two) {
            token = "Qu+YNpUAfwWfeTVZOgbUNTsp8P+yKCENh/OZPxAvTw3+MDF4xT/k8cXQrNmVa9S6nMG0+bTm2WqJJqjtqscgZg==";
            connect(token);
        }
        if (v.getId() == R.id.btn_kefu) {
//            token = "Qu+YNpUAfwWfeTVZOgbUNTsp8P+yKCENh/OZPxAvTw3+MDF4xT/k8cXQrNmVa9S6nMG0+bTm2WqJJqjtqscgZg==";
//            connect(token);
        }
        if (v.getId() == R.id.get_token) {
//            token = "Qu+YNpUAfwWfeTVZOgbUNTsp8P+yKCENh/OZPxAvTw3+MDF4xT/k8cXQrNmVa9S6nMG0+bTm2WqJJqjtqscgZg==";
//            connect(token);
            HttpUtils utils = new HttpUtils();
            utils.getToken();

        }

//        RongIM.getInstance().startPrivateChat(MainActivity.this, "123456", "标题");
    }

    private void startConversationList() {
        Map<String, Boolean> map = new HashMap<>();
        map.put(Conversation.ConversationType.PRIVATE.getName(), false); // 会话列表需要显示私聊会话, 第二个参数 true 代表私聊会话需要聚合显示
        map.put(Conversation.ConversationType.GROUP.getName(), false);  // 会话列表需要显示群组会话, 第二个参数 false 代表群组会话不需要聚合显示

        RongIM.getInstance().startConversationList(this, map);
    }

    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);
                    startConversationList();
//                    startCustomerServiceChat();
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

    private void startCustomerServiceChat() {
        //首先需要构造使用客服者的用户信息
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();

        /**
         * 启动客户服聊天界面。
         *
         * @param context           应用上下文。
         * @param customerServiceId 要与之聊天的客服 Id。
         * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
         * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
         */
        RongIM.getInstance().startCustomerServiceChat(this, "KEFU148126646226618", "在线客服",csInfo);

    }

}
