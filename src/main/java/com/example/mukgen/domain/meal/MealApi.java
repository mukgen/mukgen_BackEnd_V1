package com.example.mukgen.domain.meal;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class MealApi {
    enum RiceType {
        BREAKFAST("조식"),
        LUNCH("중식"),
        DINNER("석식");

        private final String tag;

        RiceType(String tag) {
            this.tag = tag;
        }
    }

    private static String zeroValue(int value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }

    public static JSONObject readJsonFromUrl(String url) {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) sb.append((char) cp);
            return new JSONObject(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getRiceInfo(String s) {
        String[] strings = s.split("<br/>");
        int v = strings.length;
        for (int i = 0; i < v; i++) {
            strings[i] = strings[i].replaceAll("\\(([^(^)]+)\\)", "").replaceAll("([.*\\-]+)", "");
            if(strings[i].length() <= 1) continue;
            while(true) {
                char b = strings[i].charAt(strings[i].length() - 1);
                if(b >= 32 && b <= 126) {
                    strings[i] = strings[i].substring(0, strings[i].length() - 1);
                } else {
                    break;
                }
            }
        }
        return Arrays.asList(strings);
    }

    public Rice getRice(String schoolTag, String schoolCode, RiceType riceType, int year, int month, int day) {
        final JSONObject data = readJsonFromUrl(String.format("https://open.neis.go.kr/hub/mealServiceDietInfo?Type=json&ATPT_OFCDC_SC_CODE=%s&SD_SCHUL_CODE=%s&MLSV_YMD=%d%s%s", schoolTag, schoolCode, year, zeroValue(month), zeroValue(day)));

        if (data.has("mealServiceDietInfo")) {
            final JSONArray array = data.getJSONArray("mealServiceDietInfo").getJSONObject(1).getJSONArray("row");
            int length = array.length();
            for (int i = 0; i < length; i++) {
                final JSONObject json = array.getJSONObject(i);
                if (json.getString("MMEAL_SC_NM").equals(riceType.tag)) {
                    return new Rice(getRiceInfo(json.getString("DDISH_NM")));
                }
            }
        }
        return null;
    }

    public static class Rice {
        private final List<String> items;

        public Rice(List<String> items) {
            this.items = items;
        }

        public List<String> getItems() {
            return items;
        }
    }
}