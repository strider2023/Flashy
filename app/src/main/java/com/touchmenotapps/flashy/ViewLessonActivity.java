package com.touchmenotapps.flashy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.touchmenotapps.flashy.adapters.SwipeStackAdapter;
import com.touchmenotapps.flashy.dao.FlashCardDAO;
import com.touchmenotapps.flashy.dao.enums.FlashCardType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import link.fls.swipestack.SwipeStack;
import ro.andreidobrescu.emojilike.Emoji;
import ro.andreidobrescu.emojilike.EmojiConfig;
import ro.andreidobrescu.emojilike.EmojiLikeTouchDetector;
import ro.andreidobrescu.emojilike.EmojiLikeView;
import ro.andreidobrescu.emojilike.IActivityWithEmoji;
import ro.andreidobrescu.emojilike.OnEmojiSelectedListener;

public class ViewLessonActivity extends AppCompatActivity implements IActivityWithEmoji {

    private List<FlashCardDAO> flashCardDAOList = new ArrayList<>();
    private SwipeStackAdapter mAdapter;

    @BindView(R.id.lessons_stack) SwipeStack mSwipeStack;
    @BindView(R.id.lessons_progress) ProgressBar lessonProgress;
    @BindView(R.id.lessons_holder) LinearLayout lessonsHolder;
    @BindView(R.id.lessons_home_btn) ImageButton lessonsHomeBtn;
    @BindView(R.id.lesson_complete_home_btn) AppCompatButton lessonComplete;
    @BindView(R.id.lessons_emoji_container) EmojiLikeView ratingView;
    @BindView(R.id.lessons_emoji_rate) LinearLayout rateLessonBtn;
    @BindView(R.id.lessons_selected_emoji_image) ImageView ratedImage;
    @BindView(R.id.lessons_selected_emoji_text) TextView ratedText;

    private EmojiLikeTouchDetector emojiLikeTouchDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lesson);
        ButterKnife.bind(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            lessonsHolder.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                    view.removeOnLayoutChangeListener(this);
                    animateRevealShow();
                }
            });
        }

        emojiLikeTouchDetector = new EmojiLikeTouchDetector();
        mAdapter = new SwipeStackAdapter(this);
        mSwipeStack.setAdapter(mAdapter);
        mSwipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                lessonProgress.setProgress(position+1);
            }

            @Override
            public void onViewSwipedToRight(int position) {
                lessonProgress.setProgress(position+1);
            }

            @Override
            public void onStackEmpty() {
                findViewById(R.id.lessons_activity_bar).setVisibility(View.GONE);
                mSwipeStack.setVisibility(View.GONE);
                findViewById(R.id.lesson_complete_holder).setVisibility(View.VISIBLE);
            }
        });

        fillWithTestData();

        EmojiConfig.with(this)
                .on(rateLessonBtn)
                .open(ratingView)
                .addEmoji(new Emoji(R.drawable.ic_sentiment_satisfied_white_36dp, "Nice"))
                .addEmoji(new Emoji(R.drawable.ic_sentiment_neutral_white_36dp, "Okayish"))
                .addEmoji(new Emoji(R.drawable.ic_sentiment_dissatisfied_white_36dp, "Not Good"))
                .setEmojiViewInAnimation(AnimationUtils.loadAnimation(this, R.anim.in_animation))
                .setEmojiViewOutAnimation(AnimationUtils.loadAnimation(this, R.anim.out_animation))
                .setBackgroundImage(R.drawable.bg_emoji_container)
                .setOnEmojiSelectedListener(new OnEmojiSelectedListener() {
                    @Override
                    public void onEmojiSelected(Emoji emoji) {
                        ratedImage.setImageResource(emoji.getDrawable());
                        ratedText.setText(emoji.getDescription());
                    }
                })
                .setup();
    }

    @OnClick({R.id.lessons_home_btn, R.id.lesson_complete_home_btn})
    void onHomePressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animateRevealClose();
        } else {
            finish();
        }
    }

    private void fillWithTestData() {
        FlashCardDAO flashCardDAO = new FlashCardDAO(this);
        flashCardDAO.setFlashCardType(FlashCardType.INTRO);
        flashCardDAO.setHeader(getIntent().getStringExtra("header"));
        flashCardDAO.setBody(getIntent().getStringExtra("description"));
        flashCardDAO.setImageURL("http://www.tele-online.com/veo/assets/images/strips/allseeingeye/chemistry/chemistry-lastframe.png");
        flashCardDAOList.add(flashCardDAO);

        flashCardDAO = new FlashCardDAO(this);
        flashCardDAO.setFlashCardType(FlashCardType.CONTENT);
        flashCardDAO.setBody("A sigma bond is created when two s orbitals overlap, when an s orbital overlaps with a p orbital, or when two p orbitals overlap \"head-on\".");
        flashCardDAO.setImageURL("http://freeflaticons.com/wp-content/uploads/2014/09/chemistry-copy-1410576872gk84n.png");
        flashCardDAOList.add(flashCardDAO);

        flashCardDAO = new FlashCardDAO(this);
        flashCardDAO.setFlashCardType(FlashCardType.CONTENT);
        flashCardDAO.setBody("A pi bond is created when two p orbitals overlap \"sideways-on\". The overlap now occurs above and below the line drawn through the two nuclei.");
        flashCardDAO.setImageURL("http://www.myiconfinder.com/uploads/iconsets/5430e8620da1751729fb29a9cd3fee43.png");
        flashCardDAOList.add(flashCardDAO);

        flashCardDAO = new FlashCardDAO(this);
        flashCardDAO.setFlashCardType(FlashCardType.CONTENT);
        flashCardDAO.setBody("In single bond, only sigma are present.Double bond: one sigma and one pi.Triple bond: one sigma and two pi.");
        flashCardDAO.setImageURL("http://www.tele-online.com/veo/assets/images/strips/allseeingeye/chemistry/chemistry-lastframe.png");
        flashCardDAOList.add(flashCardDAO);

        flashCardDAO = new FlashCardDAO(this);
        flashCardDAO.setFlashCardType(FlashCardType.CONTENT_ACTIVITY);
        flashCardDAO.setBody("Let's quickly recap the concepts so far.");
        flashCardDAO.setImageURL("http://freeflaticons.com/wp-content/uploads/2014/09/chemistry-copy-1410576872gk84n.png");
        flashCardDAOList.add(flashCardDAO);

        lessonProgress.setMax(flashCardDAOList.size());
        mAdapter.setData(flashCardDAOList);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealShow() {
        if(getIntent().getIntExtra("anim_x", 0) > 0 &&
            getIntent().getIntExtra("anim_y", 0) > 0) {
            Animator mAnimator = ViewAnimationUtils.createCircularReveal(lessonsHolder,
                    getIntent().getIntExtra("anim_x", lessonsHolder.getWidth() / 2),
                    getIntent().getIntExtra("anim_y", 0), 0, lessonsHolder.getHeight());
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    lessonsHolder.setVisibility(View.VISIBLE);
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateRevealClose() {
        if(getIntent().getIntExtra("anim_x", 0) > 0 &&
                getIntent().getIntExtra("anim_y", 0) > 0) {
            Animator mAnimator = ViewAnimationUtils.createCircularReveal(lessonsHolder,
                    getIntent().getIntExtra("anim_x", lessonsHolder.getWidth() / 2),
                    getIntent().getIntExtra("anim_y", 0), lessonsHolder.getHeight(), 0);
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lessonsHolder.setVisibility(View.INVISIBLE);
                    super.onAnimationEnd(animation);
                    ViewLessonActivity.super.onBackPressed();
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animateRevealClose();
        } else {
            finish();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        boolean shouldCallSuper = emojiLikeTouchDetector.dispatchTouchEvent(event);
        if (shouldCallSuper)
            return super.dispatchTouchEvent(event);
        return false;
    }

    @Override
    public void configureEmojiLike(EmojiConfig config) {
        emojiLikeTouchDetector.configure(config);
    }
}
