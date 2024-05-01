package com.example.pricechecker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PrivacyPolicy extends AppCompatActivity {
    ImageButton backbtn_pp;
    ScrollView scrollView;

    TextView policy;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        backbtn_pp = findViewById(R.id.back_btn_privacy_policy);
        scrollView = findViewById(R.id.scrollview_pp);
        policy = findViewById(R.id.pp_policy);

        String longtext = "These Terms and Conditions govern your use of the Price Checker mobile application operated by IT32S2." +
                "Please read these Terms of Service carefully before using the Service.\n" +
                "\n" +
                "        By accessing or using the Service you agree to be bound by these Terms. If you disagree with any part of the terms then you may not access the Service.\n" +
                "\n" +
                "        1. Description of Service\n" +
                "\n" +
                "        The Price Checker app is designed to provide users with information about prices of various products available in the market. This information is gathered from publicly available sources and may not always be accurate or up-to-date. The app is intended for informational purposes only and should not be relied upon as the sole basis for making purchasing decisions.\n" +
                "\n" +
                "        2. Use License\n" +
                "\n" +
                "        Permission is granted to temporarily download one copy of the Price Checker app for personal, non-commercial transitory viewing only. This is the grant of a license, not a transfer of title, and under this license you may not:\n" +
                "\n" +
                "        *Modify or copy the materials;\n" +
                "        *Use the materials for any commercial purpose, or for any public display (commercial or non-commercial);\n" +
                "        *Attempt to decompile or reverse engineer any software contained in the Price Checker app;\n" +
                "        *Remove any copyright or other proprietary notations from the materials; or\n" +
                "        *Transfer the materials to another person or \"mirror\" the materials on any other server.\n" +
                "\n" +
                "        3. Disclaimer\n" +
                "\n" +
                "        The materials within the Price Checker app are provided on default basis. We make no warranties, expressed or implied, and hereby disclaim and negate all other warranties including, without limitation, implied warranties or conditions of merchantability, fitness for a particular purpose, or non-infringement of intellectual property or other violation of rights.\n" +
                "\n" +
                "        Further, we do not warrant or make any representations concerning the accuracy, likely results, or reliability of the use of the materials on its website or otherwise relating to such materials or on any sites linked to the app.\n" +
                "\n" +
                "        4. Limitations\n" +
                "\n" +
                "        In no event shall we or our suppliers be liable for any damages (including, without limitation, damages for loss of data or profit, or due to business interruption) arising out of the use or inability to use the Price Checker app, even if we or our authorized representative has been notified orally or in writing of the possibility of such damage. Because some jurisdictions do not allow limitations on implied warranties, or limitations of liability for consequential or incidental damages, these limitations may not apply to you.\n" +
                "\n" +
                "        5. Accuracy of materials\n" +
                "\n" +
                "        The materials appearing in the Price Checker app could include technical, typographical, or photographic errors. We do not warrant that any of the materials on the app are accurate, complete or current. We may make changes to the materials contained in the app at any time without notice. However, we do not make any commitment to update the materials.\n" +
                "\n" +
                "        6. Links\n" +
                "\n" +
                "        We have not reviewed all of the sites linked to its app and are not responsible for the contents of any such linked site. The inclusion of any link does not imply endorsement by us of the site. Use of any such linked website is at the user's own risk.\n" +
                "\n" +
                "        7. Modifications\n" +
                "\n" +
                "        We may revise these terms of service for the Price Checker app at any time without notice. By using this app you are agreeing to be bound by the then current version of these terms of service.\n" +
                "\n" +
                "        8. Governing Law\n" +
                "\n" +
                "        These terms and conditions are governed by and construed in accordance with the laws of the Philippines and you irrevocably submit to the exclusive jurisdiction of the courts in that State or location.\n" +
                "\n" +
                "        9. Contact Us\n" +
                "\n" +
                "        If you have any questions about these Terms, please contact us.\n" +
                "\n" +
                "        By using the Price Checker app, you signify your acceptance of these Terms. If you do not agree to these Terms, please do not use the Price Checker app.";

        policy.setText(longtext);

        backbtn_pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and go back to the previous screen
                finish();
            }
        });
    }


}