package com.kevin.viewlibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.kevin.viewlibrary.R;
import com.kevin.viewlibrary.dialog.animutils.AnimationsContainer;


/**
 * Created by ${张奎勋} on 2018/5/23.
 * 加载中对话框
 */
public class Load extends Dialog {
    private AnimationsContainer.FramesSequenceAnimation animation;

    public Load(@NonNull Context context) {
        this(context, R.style.loadDialog);
    }

    private Load(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        ImageView imageView = new ImageView(context);
        setContentView(imageView);

        if (animation == null)
            animation = AnimationsContainer.getInstance(R.array.loading_anim, 8)
                    .createProgressDialogAnim(imageView);
    }

    @Override
    public void show() {
        super.show();
        if (animation != null)
            animation.start();
    }

    @Override
    public void dismiss() {
        if (animation != null)
            animation.stop();
        super.dismiss();
    }
}
