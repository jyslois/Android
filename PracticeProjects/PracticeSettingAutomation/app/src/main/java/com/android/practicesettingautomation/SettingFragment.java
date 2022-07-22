package com.android.practicesettingautomation;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // 앱의 설정 화면을 구성하는 설정 XML 파일 적용하기
        setPreferencesFromResource(R.xml.settings, rootKey);

        // 설정한 내용을 summary에 출력하기
        // findPreference() 함수로 설정 객체를 획득하기
        EditTextPreference idPreference = findPreference("id");
        ListPreference colorPreference = findPreference("color");

        // 사용자가 선택한 데이터를 그대로 Summary에 지정하기 위해 SimpleSummaryProvider을 이용
        colorPreference.setSummaryProvider(ListPreference.SimpleSummaryProvider.getInstance());

        // 사용자가 입력한 데이터를 이용해 로직을 실행한 후 그 결과를 Summary에 저장하기 위해해 SummarProvider을 구현한 객체를 이용
        idPreference.setSummaryProvider(new Preference.SummaryProvider() {
            @Override
            public CharSequence provideSummary(Preference preference) {
                String text = ((EditTextPreference)preference).getText();
                if (TextUtils.isEmpty(text)) {
                    return "설정이 되지 않았습니다. ";
                } else {
                    return "설정된 ID 값은 : " + text + " 입니다.";
                }
            }
        });

        // 설정이 변경되는 순간을 감지해서 변경된 값을 이용하는 경우
        // Preference.OnPreferenceChangeListener을 이용하는 방법: 각 Preference 객체마다 이벤트 핸들러를 직접 지정하여 설정 내용이 변경되는 순간의 이벤트를 처리한다.
        idPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference prefernece, Object newValue) {
                // key 값
                String key = prefernece.getKey();
                // 설정 값
                String value = (String) newValue;
                Log.d("kkang", key + " changed..." + value);
                return true;
            }
        });
    }
}
