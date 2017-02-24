package com.kaka.smargame.widget.dialog;

import android.content.Context;

import com.kaka.smargame.R;


public class DialogHelper {
	
	public static CommonDialog getPinterestDialog(Context context) {
		return new CommonDialog(context, R.style.dialog_common);
	}

	public static CommonDialog getPinterestDialogCancelable(Context context) {
		CommonDialog dialog = new CommonDialog(context,
				R.style.dialog_common);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		return dialog;
	}
}
