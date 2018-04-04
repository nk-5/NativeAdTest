package com.example.ke_nakagawa.nativeadtest;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mopub.nativeads.MoPubAdAdapter;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.ViewBinder;

import java.util.EnumSet;

public class Mopub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub);

        final ListView listView = (ListView) findViewById(R.id.native_list_view);

        final EnumSet<RequestParameters.NativeAdAsset> desiredAssets = EnumSet.of(
                RequestParameters.NativeAdAsset.TITLE,
                RequestParameters.NativeAdAsset.TEXT,
                RequestParameters.NativeAdAsset.ICON_IMAGE,
                RequestParameters.NativeAdAsset.MAIN_IMAGE,
                RequestParameters.NativeAdAsset.CALL_TO_ACTION_TEXT);

        RequestParameters requestParameters = new RequestParameters.Builder()
                .desiredAssets(desiredAssets)
                .build();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getApplicationContext(),
                android.R.layout.simple_list_item_1);
        for (int i = 0; i < 100; ++i) {
            adapter.add("Item " + i);
        }

        MoPubAdAdapter adAdapter = new MoPubAdAdapter(this, adapter, new MoPubNativeAdPositioning.MoPubServerPositioning());

        final MoPubStaticNativeAdRenderer staticNativeAdRenderer = new MoPubStaticNativeAdRenderer(
                new ViewBinder.Builder(R.layout.native_ad_list_item)
                    .titleId(R.id.native_title)
                    .textId(R.id.native_text)
                    .mainImageId(R.id.native_main_image)
                    .iconImageId(R.id.native_icon_image)
                    .callToActionId(R.id.native_cta)
                    .privacyInformationIconImageId(R.id.native_privacy_information_icon_image)
                    .build());
        adAdapter.registerAdRenderer(staticNativeAdRenderer);
        listView.setAdapter(adAdapter);

        // TODO: set adUnitId
        adAdapter.loadAds("11a17b188668469fb0412708c3d16813", requestParameters);
    }
}
