package simpletest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author shane
 *         <p>
 *         Test File Read
 */

public class ReadStringFile {
    public static void main(String[] args) {

        String[] names = new String[]{
                "1./MT8321_AP/packages/apps/MT8321_ORA_APP/app/src/main/res/values",
                "2./MT8321_AP/frameworks/base/packages/Keyguard/res/values-zh-rCN",
                "3./MT8321_AP/frameworks/base/core/res/res/values-zh-rCN",
                "4./MT8321_AP/frameworks/base/packages/SettingsLib/res/values-zh-rCN",
                "5./MT8321_AP/packages/apps/FOTA/res/values-zh-rCN",
                "6./MT8321_AP/packages/apps/SetupWizard/app/src/main/res/values-zh-rCN",
                "7./MT8321_AP/packages/apps/Settings/res/values-zh-rCN",
                "8./MT8321_AP/vendor/mediatek/proprietary/packages/apps/Settings/res/values-zh-rCN",
                "9./MT8321_AP/vendor/mediatek/proprietary/packages/apps/BatteryWarning/res/values-zh-rCN",
                "10./MT8321_AP/vendor/mediatek/proprietary/packages/apps/SystemUpdate/res/values-zh-rCN",
                "11./MT8321_AP/vendor/archermind/SerialPortProfile/DeviceSPP/device/src/main/res/values",
                "12./MT8321_AP/vendor/archermind/SerialPortProfile/DeviceSPP/devicebtcore/src/main/res/values",
                "13./MT8321_AP/vendor/archermind/SerialPortProfile/PhoneSPP/phone/src/main/res/values",
                "14./MT8321_AP/vendor/archermind/ApiTest/app/src/main/res/values"
        };

        Map<String, String> map = new HashMap<>(10000);
        String fileName3 = "/home/maoxunlei/orange_code/submit/MT8321_ORA_APP/finalString.log";
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName3));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        for (int k = 0; k < 14; k++) {
            map.clear();
            String fileName = "/home/maoxunlei/orange_code/submit/MT8321_ORA_APP/" + (k+1) + ".txt";

            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("file not exist");
                return;
            }
            BufferedReader br = null;

            try {

                br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

                String str;
                String key;
                while ((str = br.readLine()) != null) {
                    if (str.startsWith("+    ")) {
                        if (str.startsWith("+    <string name")) {
                            System.out.println(str);
                            int i = str.indexOf("<");
                            int j = str.indexOf(">", i + 1);
                            if (i != -1 && j != -1) {
                                key = str.substring(i, j);
                                map.put(key, str);
                            }
                        } else {
                            map.put(str, str);
                        }
                    }
                }
                Set<String> keySet = map.keySet();
                bw.write(names[k]);
                bw.write("\n");
                bw.flush();
                for (String _key : keySet) {
                    bw.write(map.get(_key));
                    bw.write("\n");
                    bw.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
