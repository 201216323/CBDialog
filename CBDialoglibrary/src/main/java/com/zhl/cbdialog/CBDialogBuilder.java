package com.zhl.cbdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.zhl.cbdialog.pedant.SweetAlert.ProgressHelper;
import com.zhl.cbdialog.pnikosis.materialishprogress.ProgressWheel;
import com.zhl.cbdialog.titanic.Titanic;
import com.zhl.cbdialog.titanic.TitanicTextView;

/**
 * 
 * �����ƣ��Ի��򹤾��� �������������Ի���Ĺ����࣬�������ò�ͬ��ʽ���Ͷ������
 * 
 * �����ˣ� ZhaoHaiLong ����ʱ�䣺2012-1-20 ����10:37:55
 * 
 *
 */
public class CBDialogBuilder {
	public static final int INDICATOR_BallPulse=0;
    public static final int INDICATOR_BallGridPulse=1;
    public static final int INDICATOR_BallClipRotate=2;
    public static final int INDICATOR_BallClipRotatePulse=3;
    public static final int INDICATOR_SquareSpin=4;
    public static final int INDICATOR_BallClipRotateMultiple=5;
    public static final int INDICATOR_BallPulseRise=6;
    public static final int INDICATOR_BallRotate=7;
    public static final int INDICATOR_CubeTransition=8;
    public static final int INDICATOR_BallZigZag=9;
    public static final int INDICATOR_BallZigZagDeflect=10;
    public static final int INDICATOR_BallTrianglePath=11;
    public static final int INDICATOR_BallScale=12;
    public static final int INDICATOR_LineScale=13;
    public static final int INDICATOR_LineScaleParty=14;
    public static final int INDICATOR_BallScaleMultiple=15;
    public static final int INDICATOR_BallPulseSync=16;
    public static final int INDICATOR_BallBeat=17;
    public static final int INDICATOR_LineScalePulseOut=18;
    public static final int INDICATOR_LineScalePulseOutRapid=19;
    public static final int INDICATOR_BallScaleRipple=20;
    public static final int INDICATOR_BallScaleRippleMultiple=21;
    public static final int INDICATOR_BallSpinFadeLoader=22;
    public static final int INDICATOR_LineSpinFadeLoader=23;
    public static final int INDICATOR_TriangleSkewSpin=24;
    public static final int INDICATOR_Pacman=25;
    public static final int INDICATOR_BallGridBeat=26;
    public static final int INDICATOR_SemiCircleSpin=27;
	/**
	 * ��ͨ�Ի���
	 */
	public static final int DIALOG_STYLE_NORMAL = R.layout.dialog;
	/**
	 * metrial���������
	 */
	public static final int DIALOG_STYLE_PROGRESS = R.layout.dialog_progress;
	/**
	 * һ������ˮƽ�����Ķ������Ľ��ȿ�
	 */
	public static final int DIALOG_STYLE_PROGRESS_TITANIC = R.layout.dialog_progress_titanic;
	/**
	 * һ���Զ��嶯�����ȿ�
	 */
	public static final int DIALOG_STYLE_PROGRESS_AVLOADING = R.layout.dialog_progress_avloading;
	/**
	 * ���Ŷ���
	 */
	public static final int DIALOG_ANIM_NORMAL = R.style.DialogAnimation;
	/**
	 * �������ϻ�������
	 */
	public static final int DIALOG_ANIM_SLID_BOTTOM = R.style.DialogAnimationSlidBottom;
	/**
	 * �������»�������
	 */
	public static final int DIALOG_ANIM_SLID_TOP = R.style.DialogAnimationSlidTop;
	/**
	 * �������󻬶�����
	 */
	public static final int DIALOG_ANIM_SLID_RIGHT = R.style.DialogAnimationSlidRight;
	/**
	 * �Ի�������ռ��Ļ��ȵı���
	 */
	public static final float WIDTHFACTOR = 0.75f;
	/**
	 * �Ի���͸������
	 */
	public static final float ALPHAFACTOR = 1.0f;
	/**
	 * �Ի�������Ļ����λ��
	 */
	public static final int DIALOG_LOCATION_TOP = 12;
	/**
	 * �Ի�������Ļ�м�λ��
	 */
	public static final int DIALOG_LOCATION_CENTER = 10;
	/**
	 * �Ի�������Ļ�ײ�λ��
	 */
	public static final int DIALOG_LOCATION_BOTTOM = 11;
	/**
	 * ��Ϣλ�ڶԻ����λ�� ����
	 */
	public static final int MSG_LAYOUT_LEFT = 1;
	/**
	 * ��Ϣλ�ڶԻ����λ�� ����
	 */
	public static final int MSG_LAYOUT_CENTER = 0;
	/**
	 * ��ǰʹ�õķ��
	 */
	private int DIALOG_STYLE_CURRENT = DIALOG_STYLE_NORMAL;
	/**
	 * ������
	 */
	private Context context;
	/**
	 * Dialog����
	 */
	private Dialog dialog;
	/**
	 * �ұߣ�ȷ������ť
	 */
	private Button confrimBtn;
	/**
	 * ��ߣ�ȡ������ť
	 */
	private Button cancleBtn;
	/**
	 * ��Ϣ�򲼾�
	 */
	ViewGroup msglayout;
	/**
	 * �Ƿ���ʾcancelbutton;
	 */
	private boolean showCancelButton = false;
	/**
	 * �Ƿ����Ի�������ȡ���Ի���
	 */
	private boolean touchOutSideCancel = false;
	private String confirmBtnTX = "ȷ��", cancleBtnTX = "ȡ��";
	private onDialogbtnClickListener btnClickListener;
	/**
	 * �Ƿ���ʾ����ͼ��
	 */
	private boolean showTopIcon = true;
	/**
	 * ���ȿ���ʽ�õ���metrial���
	 */
	private ProgressHelper mProgressHelper;
	private TitanicTextView mTitanicTXview;
	private Titanic titanic;
	protected int count = -1;
	/**
	 * ���ȿ�ʱʱ��
	 */
	private int outOfTime = 1000;
	private onProgressOutTimeListener mProgressOutTimeListener;
	private TextView dialogTitle, dialogMsg;
	/**
	 * �Ƿ���ʾȷ�ϰ�ť
	 */
	private boolean showConfirmBtn = true;
	/**
	 * ������
	 */
	private AVLoadingIndicatorView mAVIndicatorView;

	/**
	 * ������һ ����һ������dialog
	 * 
	 * @param context
	 */
	public CBDialogBuilder(Context context) {
		this(context, DIALOG_STYLE_NORMAL);
	}

	// �ع����캯�� �����û�ʹ���ڲ��������ʱʹ��
	/**
	 * ��������
	 * 
	 * @param context
	 *            ������
	 * @param layoutStyle
	 *            �Ի�����
	 */
	public CBDialogBuilder(Context context, int layoutStyle) {
		this(context, layoutStyle, false);
	}

	/**
	 * ��������
	 * 
	 * @param context
	 * @param layoutStyle
	 *            ������ʽ
	 * @param isSystemAlert
	 *            �Ƿ���ϵͳ����service�ȵط��õ�ϵͳ��������activity��
	 */
	public CBDialogBuilder(Context context, int layoutStyle,
			boolean isSystemAlert) {
		this(context, layoutStyle, isSystemAlert, WIDTHFACTOR, ALPHAFACTOR);
	}

	/**
	 * ��������
	 * 
	 * @param context
	 *            ������
	 * @param layoutStyle
	 *            �Ի��򲼾���ʽ
	 * @param widthcoefficient
	 *            �Ի�����ʱռ��Ļ��ȵı��أ�0-1��
	 */
	public CBDialogBuilder(Context context, int layoutStyle,
			float widthcoefficient) {
		this(context, layoutStyle, false, widthcoefficient, ALPHAFACTOR);
	}

	/**
	 * �Ի�����������
	 * 
	 * @param context
	 *            ������
	 * @param layoutStyle
	 *            ��ʽ
	 * @param widthcoefficient
	 *            �Ի�������ռ��Ļ��ȵı��أ�0-1��
	 * @param alpha
	 *            �Ի���͸����
	 */
	public CBDialogBuilder(Context context, int layoutStyle,
			float widthcoefficient, float alpha) {
		this(context, layoutStyle, false, widthcoefficient, alpha);
	}

	/**
	 * ������
	 * 
	 * @param context
	 * @param layoutStyle
	 *            ������ʽ
	 * @param isSystemAlert
	 *            �Ƿ���ϵͳ����service�ȵط��õ�ϵͳ��������activity��
	 * @param widthcoefficient
	 *            �Ի�������ռ��Ļ��ȵı��أ�0-1��
	 * @param alpha
	 *            �Ի���͸����
	 */
	public CBDialogBuilder(Context context, int layoutStyle,
			boolean isSystemAlert, float widthcoefficient, float alpha) {
		this.DIALOG_STYLE_CURRENT = layoutStyle;
		// theme Ҫ����һ����ʽȥ��ϵͳ�Ի���ı���
		Dialog dialog = new Dialog(context, R.style.Dialog);
		// ���öԻ�����
		dialog.setContentView(layoutStyle);
		Window window = dialog.getWindow();
		// �Ƿ�ϵͳ������
		if (isSystemAlert) {
			window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		}
		// ��ȡ��Ļ���
		DisplayMetrics metrics = new DisplayMetrics();
		window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int screenwidth = metrics.widthPixels;
		int width = 0;
		if (widthcoefficient > 0&&widthcoefficient<1) {
			width = (int) (screenwidth * widthcoefficient);
		} else {
			width = (int) (screenwidth * WIDTHFACTOR);
		}
		// ���öԻ�����
		window.getAttributes().width = width;

		// ����͸��
		WindowManager.LayoutParams lp = window.getAttributes();
		if (alpha > 0&&alpha<=1) {
			lp.alpha = alpha;
		} else {
			lp.alpha = ALPHAFACTOR;
		}
		window.setAttributes(lp);
		// ���ö�����ʽ
		window.setWindowAnimations(DIALOG_ANIM_NORMAL);
		this.context = context;
		this.dialog = dialog;
		if (layoutStyle == DIALOG_STYLE_PROGRESS) {
			showConfirmButton(false);
			mProgressHelper = new ProgressHelper(context);
			mProgressHelper
					.setProgressWheel((ProgressWheel) getView(R.id.progressWheel));
		}
		if (layoutStyle == DIALOG_STYLE_PROGRESS_TITANIC) {
			showConfirmButton(false);
			mTitanicTXview = getView(R.id.progressTitanic);
		}
		if(layoutStyle == DIALOG_STYLE_PROGRESS_AVLOADING){
			showConfirmButton(false);
			mAVIndicatorView = getView(R.id.progressAVloading);
		}
	}

	/**
	 * �����Ի���
	 * 
	 * @return Dialog
	 */
	public Dialog create() {
		if(confrimBtn==null){
			confrimBtn = (Button) getView(R.id.dialog_posi_btn);
		}
		
		if(showConfirmBtn){
			confrimBtn.setVisibility(View.VISIBLE);
		}else{
			confrimBtn.setVisibility(View.GONE);
		}
		if(cancleBtn==null){
			cancleBtn = (Button) getView(R.id.dialog_neg_btn);
		}
		// �ж��Ƿ���Ҫ����ȡ����ť
		if (confrimBtn != null && !showCancelButton
				&& DIALOG_STYLE_CURRENT == DIALOG_STYLE_NORMAL) {
			LayoutParams params = (LayoutParams) confrimBtn
					.getLayoutParams();
			params.setMargins(
					(int) context.getResources().getDimension(
							R.dimen.dialog_btn_single_LeftRightmargin),
					0,
					(int) context.getResources().getDimension(
							R.dimen.dialog_btn_single_LeftRightmargin), 0);
		} else {
			if (cancleBtn != null) {
				cancleBtn.setVisibility(View.VISIBLE);
			}
		}
		if (confrimBtn != null) {
			confrimBtn.setText(confirmBtnTX);
			if (btnClickListener == null) {
				confrimBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			}
		}
		if (cancleBtn != null) {
			cancleBtn.setText(cancleBtnTX);
			if (btnClickListener == null) {
				cancleBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
			}
		}
		if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS
				|| DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS_TITANIC||DIALOG_STYLE_CURRENT==DIALOG_STYLE_PROGRESS_AVLOADING) {
			dialog.setCanceledOnTouchOutside(false);
			new CountDownTimer(800 * 7 * outOfTime, 800) {
				public void onTick(long millisUntilFinished) {
					if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS) {
						count++;
						switch (count % 7) {
						case 0:
							mProgressHelper.setBarColor(context.getResources()
									.getColor(R.color.blue_btn_bg_color));
							break;
						case 1:
							mProgressHelper.setBarColor(context.getResources()
									.getColor(R.color.material_deep_teal_50));
							break;
						case 2:
							mProgressHelper.setBarColor(context.getResources()
									.getColor(R.color.success_stroke_color));
							break;
						case 3:
							mProgressHelper.setBarColor(context.getResources()
									.getColor(R.color.material_deep_teal_20));
							break;
						case 4:
							mProgressHelper.setBarColor(context.getResources()
									.getColor(R.color.material_blue_grey_80));
							break;
						case 5:
							mProgressHelper.setBarColor(context.getResources()
									.getColor(R.color.warning_stroke_color));
							break;
						case 6:
							mProgressHelper.setBarColor(context.getResources()
									.getColor(R.color.success_stroke_color));
							break;
						}
					} else if(DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS_TITANIC){
						if (titanic == null) {
							titanic = new Titanic();
							titanic.start(mTitanicTXview);
						}
					}
				}

				public void onFinish() {
					if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS) {
						count = -1;
						if (mProgressHelper.getProgressWheel() != null) {
							mProgressHelper.getProgressWheel().setVisibility(
									View.GONE);
						}
					} else if(DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS_TITANIC) {
						titanic.cancel();
						mTitanicTXview.setVisibility(View.GONE);
					}else{
						mAVIndicatorView.setVisibility(View.GONE);
					}
					FrameLayout erroricon = getView(R.id.error_frame);
					if (erroricon != null) {
						erroricon.setVisibility(View.VISIBLE);
					}
					if (confrimBtn != null) {
						confrimBtn.setVisibility(View.VISIBLE);
					}
					if (dialogMsg == null) {
						dialogMsg = getView(R.id.dialog_message);
					} else {
						dialogMsg.setText(R.string.progress_dialog_outtime_msg);
					}
					if (mProgressOutTimeListener != null) {
						mProgressOutTimeListener.onProgressOutTime(dialog,
								dialogMsg);
					}
				}
			}.start();
		}
		return dialog;
	}

	public CBDialogBuilder setProgressTitanicText(Object msg) {
		if (mTitanicTXview != null
				&& DIALOG_STYLE_CURRENT == DIALOG_STYLE_PROGRESS_TITANIC) {
			mTitanicTXview.setText(parseParam(msg));
		}
		return this;
	}

	/**
	 * set weather show cancel button, if true,the Dialog show two buttons
	 * 
	 * @param showCancelButton
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder showCancelButton(boolean showCancelButton) {
		this.showCancelButton = showCancelButton;
		return this;
	}

	public CBDialogBuilder showConfirmButton(boolean showConfirmbtn){
		this.showConfirmBtn = showConfirmbtn;
		return this;
	}
	/**
	 * ���öԻ������
	 * 
	 * @param title
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setTitle(Object title) {

		dialogTitle = getView(R.id.dialog_title);
		if (dialogTitle != null) {
			if (title != null) {
				dialogTitle.setText(parseParam(title));
			} else {
				dialogTitle.setVisibility(View.GONE);
			}
		}
		return this;
	}

	/**
	 * ���Ի������ö���
	 * 
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setDialogAnimation(int resId) {
		this.dialog.getWindow().setWindowAnimations(resId);
		return this;
	}

	/**
	 * ���öԻ����λ��
	 * 
	 * @param location
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setDialoglocation(int location) {
		Window window = this.dialog.getWindow();
		switch (location) {
		case DIALOG_LOCATION_CENTER:
			window.setGravity(Gravity.CENTER);
			break;
		case DIALOG_LOCATION_BOTTOM:
			window.setGravity(Gravity.BOTTOM);
			break;
		case DIALOG_LOCATION_TOP:
			window.setGravity(Gravity.TOP);
			break;
		default:
			break;
		}
		return this;
	}

	/**
	 * ���ý��ȿ�ʱ����
	 * 
	 * @param duration
	 *            ��ʱʱ�� ���ú�õ���ʱ��Ϊ800*7*duration�� duration Ĭ��1000
	 * @param progressOutTimeListener
	 */
	public CBDialogBuilder setOnProgressOutTimeListener(int duration,
			onProgressOutTimeListener progressOutTimeListener) {
		this.mProgressOutTimeListener = progressOutTimeListener;
		this.outOfTime = duration;
		if(outOfTime==-1){
			
		}
		return this;
	}

	/**
	 * ���öԻ������Ϣ����
	 * 
	 * @param message
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setMessage(Object message) {
		dialogMsg = getView(R.id.dialog_message);
		if (dialogMsg != null) {
			if (message != null) {
				dialogMsg.setText(parseParam(message));
			} else {
				dialogMsg.setVisibility(View.GONE);
			}
		}
		return this;
	}

	/**
	 * ������Ϣ�ڶԻ����е�λ�� MSG_LAYOUT_LEFT ���� MSG_LAYOUT_CENTER ���� Ĭ�Ͼ���
	 * 
	 * @param layout
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setMessageGravity(int layout) {
		TextView dialogcontent = getView(R.id.dialog_message);
		if (dialogcontent != null && layout > 0) {
			if (layout == MSG_LAYOUT_LEFT) {
				dialogcontent.setGravity(Gravity.LEFT);
			} else if (layout == MSG_LAYOUT_CENTER) {
				dialogcontent.setGravity(Gravity.CENTER);
			}
		}
		return this;
	}

	/**
	 * ����ť���ûص�����
	 * 
	 * @param btnClickListener
	 *            ��ť�Ļص�����
	 * @param isDissmiss
	 *            �����ť���Ƿ�ȡ���Ի���
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setButtonClickListener(final boolean isDissmiss,
			final onDialogbtnClickListener btnClickListener) {
		if (DIALOG_STYLE_CURRENT != DIALOG_STYLE_NORMAL) {
			return this;
		}
		this.btnClickListener = btnClickListener;
		// ����ȷ�ϰ�ť
		final Button btnConfirm = getView(R.id.dialog_posi_btn);

		// ����ť�󶨼�����
		btnConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isDissmiss) {
					dialog.dismiss();
				}
				if (btnClickListener != null) {
					btnClickListener.onDialogbtnClick(context, dialog,
							onDialogbtnClickListener.BUTTON_CONFIRM);
				}
			}
		});

		// ����������ť
		final Button btnCancel = getView(R.id.dialog_neg_btn);
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isDissmiss) {
					dialog.dismiss();
				}
				if (btnClickListener != null) {
					btnClickListener.onDialogbtnClick(context, dialog,
							onDialogbtnClickListener.BUTTON_CANCEL);
				}
			}
		});
		return this;
	}

	public CBDialogBuilder setConfirmBtnDrawable(int resID){
		if(confrimBtn==null){
			confrimBtn = (Button) getView(R.id.dialog_posi_btn);
		}
		if(confrimBtn!=null&&resID!=-1){
			confrimBtn.setBackgroundResource(resID);
		}
		return this;
	}
	
	public CBDialogBuilder setCancelBtnDrawable(int resID){
		if(cancleBtn==null){
			cancleBtn = (Button) getView(R.id.dialog_neg_btn);
		}
		if(cancleBtn!=null&&resID!=-1){
			cancleBtn.setBackgroundResource(resID);
		}
		return this;
	}

	/**
	 * ���Ի����м���������Ϊһ���Զ���view
	 * 
	 * @param v
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setView(View v) {
		msglayout = getView(R.id.dialog_msg_layout);
		// ɾ��ԭ����textview
		msglayout.removeAllViews();
		// ����µ�view
		msglayout.addView(v);

		return this;
	}

	/**
	 * �����û�����Ĳ����ļ�����view���Ի���
	 * 
	 * @param nameInput
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setView(int nameInput) {
		ViewGroup msglayout = getView(R.id.dialog_msg_layout);
		// ��Ҫ������ӵĲ����ļ��ĸ��ؼ���false��ʾ����Ҫinflate������ӵ����ؼ��£��������Լ����
		return setView(LayoutInflater.from(context).inflate(nameInput,
				msglayout, false));
	}

	/**
	 * �Ƿ���ʾ����ͼ��
	 * 
	 * @param showIcon
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder showIcon(boolean showIcon) {
		this.showTopIcon = showIcon;
		View wraningIcon = getView(R.id.warning_frame);
		if (!showTopIcon) {
			wraningIcon.setVisibility(View.GONE);
		}
		ImageView customIcon = getView(R.id.custom_icon);
		if (!showTopIcon) {
			customIcon.setVisibility(View.GONE);
		}
		return this;
	}

	public CBDialogBuilder setItems(String[] items,
			final onDialogItemClickListener listener) {
		return setItems(items, listener, -1);
	}

	/**
	 * ���Ի�������һ�������б�
	 * 
	 * @param items
	 * @param listener
	 * @param curSelectedPos
	 *            ��ǰѡ�е�position
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setItems(String[] items,
			final onDialogItemClickListener listener, int curSelectedPos) {
		if (DIALOG_STYLE_CURRENT != DIALOG_STYLE_NORMAL) {
			return this;
		}
		this.showIcon(false);
		// ���Ի�������listview
		setView(R.layout.optiondialog_list_view);
		// ��listview ��������
		ListView listview = getView(android.R.id.list);

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
		// R.layout.item_option_text, items);
		final DialogItemAdapter adapter = new DialogItemAdapter(items,
				curSelectedPos);
		listview.setAdapter(adapter);
		// ��listview�����ѡ�����ü�����
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// �õ����߾����Ƿ�رնԻ���
				// dialog.dismiss();
				adapter.setSelectedPos(position);
				adapter.notifyDataSetChanged();
				if (listener != null) {
					listener.onDialogItemClick(adapter, context,
							CBDialogBuilder.this, dialog, position);
				}
			}
		});

		return this;
	}

	/**
	 * ���ط�������һ����Դ�ļ�
	 * 
	 * @param resId
	 * @param listener
	 * @return CBDialogBuilder
	 */

	public CBDialogBuilder setItems(int resId,
			onDialogItemClickListener listener) {
		String[] items = context.getResources().getStringArray(resId);
		return setItems(items, listener, -1);
	}

	/**
	 * ����һ��������ԴID
	 * 
	 * @param resId
	 * @param listener
	 * @param selectedPos
	 *            ��ǰѡ����
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setItems(int resId,
			onDialogItemClickListener listener, int selectedPos) {
		String[] items = context.getResources().getStringArray(resId);
		return setItems(items, listener, selectedPos);
	}

	/**
	 * set wheather dialog cancelable when touch outside
	 * 
	 * @param touchOutSideCancel
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setTouchOutSideCancelable(boolean touchOutSideCancel) {
		this.touchOutSideCancel = touchOutSideCancel;
		if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_NORMAL) {
			this.dialog.setCanceledOnTouchOutside(touchOutSideCancel);
		}
		return this;
	}

	/**
	 * set the dialog wheather can cancelable;
	 * 
	 * @param cancleable
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setCancelable(boolean cancleable) {
		this.dialog.setCancelable(cancleable);
		return this;
	}

	/**
	 * ����ȷ����ť����
	 * 
	 * @param confrim
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setConfirmButtonText(Object confrim) {
		this.confirmBtnTX = getString(confrim);
		return this;
	}

	/**
	 * �����Զ���ͼ��
	 * 
	 * @param ResID
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setCustomIcon(int ResID) {
		ImageView icon = getView(R.id.custom_icon);
		FrameLayout warningFrame = getView(R.id.warning_frame);
		if (icon != null && warningFrame != null && ResID > 0) {
			icon.setVisibility(View.VISIBLE);
			warningFrame.setVisibility(View.GONE);
			icon.setImageResource(ResID);
		}
		return this;
	}

	private String getString(Object confrim) {
		if (confrim == null) {
			return this.confirmBtnTX;
		}
		if (confrim instanceof String) {
			return (String) confrim;
		}
		if (confrim instanceof Integer) {
			return context.getString((Integer) confrim);
		}
		return this.confirmBtnTX;
	}

	/**
	 * ����ȡ����ť����
	 * 
	 * @param confrim
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setCancelButtonText(Object confrim) {
		this.cancleBtnTX = getString(confrim);
		return this;
	}

	/**
	 * ���ý���
	 * 
	 * @param indicator
	 *            AVLoadingIndicatorView.BallPulse BallClipRotatePulse �ȵ�
	 *            .BallPulse, .BallGridPulse, .BallClipRotate,
	 *            .BallClipRotatePulse, .SquareSpin, .BallClipRotateMultiple,
	 *            .BallPulseRise, .BallRotate,
	 *
	 * @return CBDialogBuilder
	 */
	public CBDialogBuilder setProgressIndicator(int indicator){
		if(mAVIndicatorView!=null){
			mAVIndicatorView.setIndicator(indicator);
		}
		return this;
	}
	
	public CBDialogBuilder setProgressIndicatorColor(int IndicatorColor){
		if(mAVIndicatorView!=null){
			mAVIndicatorView.setIndicatorColor(IndicatorColor);
		}
		return this;
	}
	
	/**
	 * �����ӿؼ�ID�õ��ӿؼ�
	 * 
	 * @param id
	 *            �ӿؼ�ID
	 * @return �����ӿؼ�
	 */
	public <T extends View> T getView(int id) {

		return (T) dialog.findViewById(id);
	}

	/**
	 * �����û���������ݣ��ַ���������ԴID
	 * 
	 * @return CBDialogBuilder
	 */
	private String parseParam(Object param) {
		// �������Դid ��ͨ�������� ��ȡ��Դ
		if (param instanceof Integer) {
			return context.getString((Integer) param);
		} else if (param instanceof String) {
			return param.toString();
		}
		return null;

	}

	// �ڲ��ӿڼ�����

	/**
	 * �Զ�������������Ի���ť���
	 * 
	 * @author zhl
	 * 
	 */
	public interface onDialogbtnClickListener {
		/**
		 * �����ֵ��������߰�ť�����ұ߰�ť��--ȷ��
		 */
		public static final int BUTTON_CONFIRM = 0;
		/**
		 * �����ֵ��������߰�ť�����ұ߰�ť��--ȡ��
		 */
		public static final int BUTTON_CANCEL = 1;

		/**
		 * 
		 * @param context
		 *            ������
		 * @param dialog
		 *            ������ĸ��Ի���
		 * @param whichBtn
		 *            ������ĸ���ť
		 */
		void onDialogbtnClick(Context context, Dialog dialog, int whichBtn);

	}

	/**
	 * �Զ�������������Ի����е�ѡ����
	 * 
	 * @author yan
	 * 
	 */
	public interface onDialogItemClickListener {

		/**
		 * 
		 * @param context
		 * @param dialogbuilder
		 *            ����
		 * @param dialog
		 * @param position
		 *            ѡ��Ǳ�
		 */
		public void onDialogItemClick(DialogItemAdapter ItemAdapter,
									  Context context, CBDialogBuilder dialogbuilder, Dialog dialog,
									  int position);

	}

	/**
	 * ���ȿ�ʱ����
	 * 
	 * @author long
	 */
	public interface onProgressOutTimeListener {

		public void onProgressOutTime(Dialog dialog, TextView dialogMsgTextView);

	}

	/**
	 * dialog�б�ѡ���������
	 * 
	 * @author long
	 */
	public class DialogItemAdapter extends BaseAdapter {
		private String[] dataArrays;
		private int selectedPos;

		public DialogItemAdapter(String[] dataArrays, int selectedPos) {
			this.dataArrays = dataArrays;
			this.selectedPos = selectedPos;
		}

		@Override
		public int getCount() {
			return dataArrays == null ? 0 : dataArrays.length;
		}

		@Override
		public Object getItem(int position) {
			return dataArrays[position];
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		public void setSelectedPos(int position) {
			this.selectedPos = position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(context).inflate(
						R.layout.item_option_text, parent, false);
				viewHolder.txView = (TextView) convertView
						.findViewById(R.id.item_tx);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			if (position == selectedPos) {
				viewHolder.txView.setTextColor(context.getResources().getColor(
						R.color.item_text_color_pressed));
				viewHolder.txView
						.setBackgroundResource(R.drawable.item_tx_background);
			} else {
				viewHolder.txView.setTextColor(context.getResources().getColor(
						R.color.item_text_color));
				viewHolder.txView
						.setBackgroundResource(R.color.color_transparent);
			}
			viewHolder.txView.setText(dataArrays[position]);
			return convertView;
		}

	}

	public class ViewHolder {
		TextView txView;
	}

	public ProgressHelper getProgressHelper() {
		return mProgressHelper;
	}

}
