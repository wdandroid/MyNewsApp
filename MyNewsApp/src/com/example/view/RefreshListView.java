package com.example.view;

import com.example.mynewsapp.R;

import android.content.Context;
import android.text.style.DynamicDrawableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RefreshListView extends ListView {

	
	Context context;
	private int starty;
	private View header;
	private int headerHeigt;
	private TextView tv_refresh_title;
	
	private static final int STATE_PULLREFRESH =0;
	
	private static final int STATE_RELEASEREFRESH =1;
	
	private static final int STATE_REFRESHING =2;

	int current_state=0;
	private ProgressBar pb_progress;
	private ImageView iv_refresh_arr;
	private RotateAnimation rotateAnimation;
	private View footer;
	
	
	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context=context;
		initHeaderview();
		initFooterView();
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context=context;

		initHeaderview();
		initFooterView();

	}

	public RefreshListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;

		initHeaderview();
		initFooterView();

	}

	
	boolean isloadmore;
	private void initFooterView() {
		// TODO Auto-generated method stub
		footer = View.inflate(context, R.layout.refresh_listview_footer, null);

		footer.measure(0, 0);
		footHeight = footer.getMeasuredHeight();
		footer.setPadding(0, -footHeight, 0, 0);
		this.addFooterView(footer);
		this.setOnScrollListener( new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
				if (scrollState==SCROLL_STATE_IDLE||scrollState==SCROLL_STATE_FLING) {
					
					
					
					if (getLastVisiblePosition()==getCount()-1&&!isloadmore) {
						System.out
								.println("RefreshListView.initFooterView()到底了");
						footer.setPadding(0, 0, 0, 0);
						
						//去加载更多
						isloadmore=true;
						refreshingInterface.loadmore();

					}
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	

	
	

	private void initHeaderview() {
		header = View.inflate(context, R.layout.refresh_header, null);
		
	    tv_refresh_title = (TextView) header.findViewById(R.id.tv_refresh_title);
		
	     pb_progress = (ProgressBar) header.findViewById(R.id.pb_progress);
	    
	     iv_refresh_arr = (ImageView) header.findViewById(R.id.iv_refresh_arr);
	     
		headerHeigt = 0;
		header.measure(0, 0);	
		headerHeigt=	header.getMeasuredHeight();
		
		
		System.out.println("RefreshListView.initview() headerHeigt"+headerHeigt);
		
		header.setPadding(0, -headerHeigt, 0, 0);
		this.addHeaderView(header); //可以支持加多个header
		
		initAnimation();
	}
	
	private void initAnimation() {
		// TODO Auto-generated method stub
		
		rotateAnimation = new RotateAnimation(0, 180, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotateAnimation.setDuration(500);
		rotateAnimation.setFillAfter(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			starty = (int) ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			
			
			if (current_state==STATE_REFRESHING) {
				break;
			}
			int endy= (int) ev.getRawY();
			
			int dy =endy-starty;
			
			if (dy>0) {//往下拉
				
				if (dy>headerHeigt) {//拉过位置。松手刷新
					
					current_state=STATE_RELEASEREFRESH;
					refreshState();
					header.setPadding(0, -headerHeigt+dy, 0, 0);

				}else //没有拉到位置
				{
					
					current_state=STATE_PULLREFRESH;
					header.setPadding(0, -headerHeigt+dy, 0, 0);
					refreshState();

				}

			}
					
			break;	
		case MotionEvent.ACTION_UP:
			
			if (current_state==STATE_PULLREFRESH) {
				header.setPadding(0, -headerHeigt, 0, 0);

			}else if (current_state==STATE_RELEASEREFRESH) {
				current_state=STATE_REFRESHING;
				header.setPadding(0, 0, 0, 0);
				refreshState();
			}  
					
			break;

		default:
			break;
		}
		
		
		return super.onTouchEvent(ev);
	}



	private void refreshState() {
		// TODO Auto-generated method stub
		switch (current_state) {
		case STATE_PULLREFRESH:
			tv_refresh_title.setText("下拉刷新");

			break;
		case STATE_RELEASEREFRESH:
			tv_refresh_title.setText("松开刷新");
            iv_refresh_arr.startAnimation(rotateAnimation);
			
            
			break;
			
	    case STATE_REFRESHING:
		
            iv_refresh_arr.clearAnimation();
	    	iv_refresh_arr.setVisibility(View.INVISIBLE);
 	    	pb_progress.setVisibility(View.VISIBLE);
			tv_refresh_title.setText("正在刷新");

			//
			refreshingInterface.refresh();
	    	
		    break;
		default:
			break;
		}
		
	}
	
	RefreshingListener refreshingInterface;
	private int footHeight;
	
	
	public void setRefreshingListener(RefreshingListener i){
		
		refreshingInterface =i;	
	}
	
	public interface RefreshingListener{
		
		public void refresh();
		
		public void loadmore();
		
	}
	
	
    public void	onRefreshComplete(){
    	current_state=STATE_PULLREFRESH;
		header.setPadding(0, -headerHeigt, 0, 0);
		pb_progress.setVisibility(View.INVISIBLE);

	}
    
    public void onLoadmoreComplete(){
    	//
    	isloadmore=false;
		footer.setPadding(0, -footHeight, 0, 0);
    }
	
	
}
