package swp3.skku.edu.squiz.SubjectiveCardPage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import swp3.skku.edu.squiz.EndPageActivity;
import swp3.skku.edu.squiz.model.CardItem;
import swp3.skku.edu.squiz.model.UserAnswerItem;


/**
 * Created by LG on 2018-05-15.
 */

public class Adapter_subjective extends RecyclerView.Adapter<ViewHolder_subjective> {

    private int subjective_content;
    private ArrayList<CardItem> subjectiveItemList = new ArrayList<>();
    private ArrayList<UserAnswerItem> userAnswerList;
    private Context context;
    private int correct_num;
    private SubjectiveCardActivity subjectiveCardActivity;

    public Adapter_subjective(int subjectivepage_content, Context context, ArrayList<CardItem> subjectiveItemList, ArrayList<UserAnswerItem> userAnswerList, SubjectiveCardActivity subjectiveCardActivity) {
        subjective_content = subjectivepage_content;
        this.context = context;
        this.subjectiveItemList = subjectiveItemList;
        this.userAnswerList = userAnswerList;
        this.subjectiveCardActivity = subjectiveCardActivity;
    }

    @Override
    public ViewHolder_subjective onCreateViewHolder(ViewGroup parent, int viewType) {
        final View subjective_view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        subjective_view = inflater.inflate(subjective_content, parent, false);
        final ViewHolder_subjective viewHolder_subjective = new ViewHolder_subjective(subjective_view);

        viewHolder_subjective.meaning.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                 if(keyCode == keyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                     int position = viewHolder_subjective.position;
                     String trueMean = subjectiveItemList.get(position).getMeaning();
                     String userMean = String.valueOf(viewHolder_subjective.meaning.getText());
                     if(trueMean.equals(userMean)){
                         Toast.makeText(context, "정답입니다", Toast.LENGTH_SHORT).show();
                         viewHolder_subjective.correct.setVisibility(View.VISIBLE);
                         userAnswerList.get(position).setAnswer(trueMean);
                         userAnswerList.get(position).setCorrect(true);
                         correct_num++;
                         if(correct_num == subjectiveItemList.size()){
                             Intent intent = new Intent(subjectiveCardActivity, EndPageActivity.class);
                             subjectiveCardActivity.startActivity(intent);
                             correct_num--;
                         }
                     }else{
                         Toast.makeText(context, "오답입니다", Toast.LENGTH_SHORT).show();
                         userAnswerList.get(position).setAnswer(userMean);
                     }
                     return true;
                }
                return false;
            }
        });
        return viewHolder_subjective;
    }

    @Override
    public void onBindViewHolder(ViewHolder_subjective holder, int position) {
        CardItem cardItem = subjectiveItemList.get(position);
        UserAnswerItem userAnswerItem = userAnswerList.get(position);
        holder.question.setText((position+1)+"번문제. "+"'"+cardItem.getWord()+"'"+" 이란?");
        holder.position = position;
        holder.meaning.setText(userAnswerItem.getAnswer());
        if(userAnswerItem.getCorrect().equals(true)){
            holder.correct.setVisibility(View.VISIBLE);
            holder.meaning.setFocusable(false);
        }else if(userAnswerItem.getCorrect().equals(false)){
            holder.correct.setVisibility(View.INVISIBLE);
            holder.meaning.setFocusable(true);
        }

    }

    @Override
    public int getItemCount() {
        return subjectiveItemList.size();
    }
}
