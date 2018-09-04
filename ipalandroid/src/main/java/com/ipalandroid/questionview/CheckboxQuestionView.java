package com.ipalandroid.questionview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ipalandroid.R;
import com.ipalandroid.common.ImageDownloader;
import com.ipalandroid.common.TouchImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents Checkbox Question View. Any question with checkboxes use this java and activity.
 * This corrently is only multichoice with more than one answer. The class creates the view from HTML,
 * validates input and send answers to the server
 *
 * @author Ngoc Nguyen modified by William Junnkin
 *
 */

public class CheckboxQuestionView extends QuestionView {
    private String TAG = "CheckboxQuestionView";
    /**
     * This class represents Choice in Multiple Choice question. Each choice
     * has a text and a value.
     *
     * @author Ngoc Nguyen modified by William Junkin
     *
     */
    private class Choice
    {
        private String text;
        private int value;

        public Choice(String cText, int cValue) {
            text= cText;
            value= cValue;
        }

        /**
         * @return the text of the current choice
         */
        public String getText() { return text; }

        /**
         * @return the value of the current choice
         */
        public int getValue() { return value; }
    }
/**
    private ArrayList<CheckboxQuestionView.Choice> choices = new ArrayList<CheckboxQuestionView.Choice>();
    private String currentChoice;
**/

    /**
     * A constructor for the MultipleChoiceQuestionView. It gets the question text,
     * populates the choices, prepares Moodle url and username for submission.
     *
     * @param questionPage: the JSoup document fetched from the server
     * @param url: Moodle URL
     * @param username: Moodle User Name
     * @param passcode: ipal Passcode
     */
    public CheckboxQuestionView(Document questionPage, String url, String username, int passcode)
    {
        super(questionPage, url, username, passcode);
        questionPage = this.questionPage;
        //Populates the choices
        Log.d(TAG,"Line 84");
        //final CheckBox mycb4 = (CheckBox) getLayout().findViewById(R.id.cb4);
        //mycb4.setText("This works");
        //mycb4.setVisibility(View.VISIBLE);
        Integer i = 0;
        Elements spans = this.questionPage.getElementsByTag("span");
        for (Element s: spans) {
            String cText = s.select("label").text();
            if (s.select("input").attr("value").length() > 0) {
                String checkboxid = "cb" + Integer.toString(i);
                final CheckBox mycb = (CheckBox) getLayout().findViewById(R.id.cb2);
                mycb.setText("works for cb2");
                mycb.setVisibility(View.VISIBLE);
                // (CheckBox) findViewById(R.id.checkboxid);

                //int cValue = Integer.parseInt(s.select("input").attr("value"));
                //choices.add(new CheckboxQuestionView.Choice(cText, cValue));
            }

        }
        //Set the question Text
        qText = this.questionPage.select("legend").text();
        //Set the Image URL if there is one
        imageURL = questionPage.select("img").attr("src");
        //Log.w("IMAGE URL", imageURL+ "   a a a");
    }

    @Override
    public View getQuestionView(Context c) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //ScrollView view = (ScrollView) inflater.inflate(R.layout.multichoice, null);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.checkbox,null);
        CheckBox CheckboxTextView = (CheckBox) layout.findViewById(R.id.cb4);
        CheckboxTextView.setText("This works");
        //LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
/**        TextView qTextView = (TextView) layout.findViewById(R.id.questionText);
        qTextView.setText(qText);

        //Download and display the image
        ImageDownloader downloader = new ImageDownloader(c);
        Bitmap img = downloader.getImage(imageURL);
        TouchImageView tiv = new TouchImageView(c);
        tiv.setImageBitmap(img);
        layout.addView(tiv);
        RadioGroup g = (RadioGroup) layout.findViewById(R.id.answerChoice);
        for (CheckboxQuestionView.Choice ch: choices) {
            RadioButton b = new RadioButton(c);
            b.setText(ch.text);
            b.setTextColor(c.getResources().getColor(R.color.view_text_color));
            //b.setTextColor(R.color.view_text_color);
            g.addView(b,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        g.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                for(int i=0; i<rg.getChildCount(); i++) {
                    RadioButton btn = (RadioButton) rg.getChildAt(i);
                    if(btn.getId() == checkedId) {
                        currentChoice = (String) btn.getText();
                        return;
                    }
                }
            }
        });**/
        return layout;
    }

    @Override
    public Boolean validateInput() {
        return true;
    }

    @Override
    public Boolean sendResult() {
/**        //SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        int valueToSend = getChoiceValueFromText(currentChoice);
        if (valueToSend != -1) {
            try {
                Jsoup.connect(url+"/mod/ipal/tempview.php?user="+username+"&p="+passcode)
                        .data("answer_id", valueToSend+"")
                        .data("a_text", "")
                        .data("question_id", question_id+"")
                        .data("active_question_id", active_question_id+"")
                        .data("course_id", course_id+"")
                        .data("user_id", user_id+"")
                        .data("submit", "Submit")
                        .data("ipal_id", ipal_id+"")
                        .data("instructor", instructor)
                        .post();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
**/        return false;
    }
/**
    private int getChoiceValueFromText(String cText) {
        for (Choice c: choices) {
            if (c.getText().equals(cText)) {
                return c.getValue();
            }
        }
        return -1;
    }
**/
    @Override
    public LinearLayout getLayout() {
        // TODO Auto-generated method stub
        return null;
    }

}
