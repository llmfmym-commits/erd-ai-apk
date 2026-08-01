package com.erd.ai;

import android.os.Bundle;
import android.webkit.PermissionRequest;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.BridgeWebChromeClient;

/**
 * ERD AI needs microphone access inside the WebView for the "Realtime Voice"
 * feature (it calls navigator.mediaDevices.getUserMedia in plain JavaScript).
 * The manifest permission (RECORD_AUDIO) alone is not enough for WebView -
 * the WebView itself must also grant the in-page permission request, which
 * this override does automatically instead of silently denying it.
 */
public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.bridge.getWebView().setWebChromeClient(new BridgeWebChromeClient(this.bridge) {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                runOnUiThread(() -> request.grant(request.getResources()));
            }
        });
    }
}
