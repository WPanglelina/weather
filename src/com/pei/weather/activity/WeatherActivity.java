package com.pei.weather.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pei.weather.R;
import com.pei.weather.entity.HeWeather;
import com.pei.weather.entity.HeWeatherData;
import com.pei.weather.ui.SuperScrollView;
import com.pei.weather.ui.SuperScrollView.OnScrollListener;

public class WeatherActivity extends Activity implements OnScrollListener,
		OnClickListener {

	private HeWeather mHeWeather;
	private TextView mWeatherCity, mWeatherStatus, mWeatherTmp,
			mWeatherAirlevel;
	private TextView mWeatherDay0, mWeatherImg0, mWeatherTmp0;
	private TextView mWeatherDay1, mWeatherImg1, mWeatherTmp1;
	private TextView mWeatherDay2, mWeatherImg2, mWeatherTmp2;
	private TextView mWeatherDay3, mWeatherImg3, mWeatherTmp3;
	private TextView mWeatherDay4, mWeatherImg4, mWeatherTmp4;
	private TextView mWeatherDay5, mWeatherImg5, mWeatherTmp5;
	private SuperScrollView mScrollView;
	private TextView mWeatherTopVisable;
	private TextView mWeatherTop;
	private TextView mPm10ViewBack;
	private TextView mPm25ViewBack;
	private LinearLayout mWeatherTodayLayout;
	private JSONObject mJsonObject;

	private TextView mWeatherAirlevelTxt, mWeatherAirlevelNum, mWeatherPm25Txt,
			mWeatherPm25View, mWeatherPm10Txt, mWeatherPm10View;

	// 建议
	private TextView mSportTxt, mWashCarTxt, mUVTxt, mShoppingTxt, mSPFTxt,
			mFishingTxt, mClothTxt, mComfortTxt;

	// 风向、日出日落、温度、体感温度
	private TextView mWindDirec, mWindPower, mSunRise, mSunSet, mHumidness,
			mBodyTemp;
	private LinearLayout sportLayout, washcarLayout, uvlLayout, fluLayout,
			spfLayout, traveLayout, clothLayout, comfortLayout;

	private LinearLayout mDetailLayout;
	private TextView mDetailImage;
	private TextView mDetailTitle;
	private TextView mDetailValue;
	private TextView mDetailContent;

	private DetailInfo mDetailInfo = new DetailInfo();

	private Map<String, String> mDetailMap = new HashMap<String, String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		initView();
		initEvent();
		initData();
		try {
			requestWeather();
		} catch (AuthFailureError e) {
			e.printStackTrace();
		}
	}

	private void initData() {
		mDetailMap.put("sport", "运动指数");
		mDetailMap.put("cw", "洗车指数");
		mDetailMap.put("uv", "紫外线指数");
		mDetailMap.put("flu", "感冒指数");
		mDetailMap.put("spf", "防嗮指数");
		mDetailMap.put("trav", "旅行指数");
		mDetailMap.put("drsg", "穿衣指数");
		mDetailMap.put("comf", "舒适指数");
	}

	private void initEvent() {
		mScrollView.setOnScrollListener(this);
		sportLayout.setOnClickListener(clickListener);
		washcarLayout.setOnClickListener(clickListener);
		uvlLayout.setOnClickListener(clickListener);
		fluLayout.setOnClickListener(clickListener);
		spfLayout.setOnClickListener(clickListener);
		traveLayout.setOnClickListener(clickListener);
		clothLayout.setOnClickListener(clickListener);
		comfortLayout.setOnClickListener(clickListener);
		mDetailLayout.setOnClickListener(this);

	}

	private void initView() {
		mWeatherCity = (TextView) findViewById(R.id.weather_city);
		mWeatherStatus = (TextView) findViewById(R.id.weather_status);
		mWeatherTmp = (TextView) findViewById(R.id.weather_tmp);
		mWeatherAirlevel = (TextView) findViewById(R.id.weather_airlevel);

		mWeatherDay0 = (TextView) findViewById(R.id.weather_day0);
		mWeatherImg0 = (TextView) findViewById(R.id.weather_img0);
		mWeatherTmp0 = (TextView) findViewById(R.id.weather_tmp0);

		mWeatherDay1 = (TextView) findViewById(R.id.weather_day1);
		mWeatherImg1 = (TextView) findViewById(R.id.weather_img1);
		mWeatherTmp1 = (TextView) findViewById(R.id.weather_tmp1);

		mWeatherDay2 = (TextView) findViewById(R.id.weather_day2);
		mWeatherImg2 = (TextView) findViewById(R.id.weather_img2);
		mWeatherTmp2 = (TextView) findViewById(R.id.weather_tmp2);

		mWeatherDay3 = (TextView) findViewById(R.id.weather_day3);
		mWeatherImg3 = (TextView) findViewById(R.id.weather_img3);
		mWeatherTmp3 = (TextView) findViewById(R.id.weather_tmp3);

		mWeatherDay4 = (TextView) findViewById(R.id.weather_day4);
		mWeatherImg4 = (TextView) findViewById(R.id.weather_img4);
		mWeatherTmp4 = (TextView) findViewById(R.id.weather_tmp4);

		mWeatherDay5 = (TextView) findViewById(R.id.weather_day5);
		mWeatherImg5 = (TextView) findViewById(R.id.weather_img5);
		mWeatherTmp5 = (TextView) findViewById(R.id.weather_tmp5);

		mWeatherAirlevelTxt = (TextView) findViewById(R.id.airlevel_txt);
		mWeatherAirlevelNum = (TextView) findViewById(R.id.airlevel_num);
		mWeatherPm25Txt = (TextView) findViewById(R.id.pm25_txt);
		mWeatherPm25View = (TextView) findViewById(R.id.pm25_view);
		mWeatherPm10Txt = (TextView) findViewById(R.id.pm10_txt);
		mWeatherPm10View = (TextView) findViewById(R.id.pm10_view);

		mSportTxt = (TextView) findViewById(R.id.sport_txt);
		mWashCarTxt = (TextView) findViewById(R.id.washcar_txt);
		mUVTxt = (TextView) findViewById(R.id.uv_txt);
		mShoppingTxt = (TextView) findViewById(R.id.flu_txt);
		mSPFTxt = (TextView) findViewById(R.id.spf_txt);
		mFishingTxt = (TextView) findViewById(R.id.travel_txt);
		mClothTxt = (TextView) findViewById(R.id.cloth_txt);
		mComfortTxt = (TextView) findViewById(R.id.comfort_txt);

		mWindDirec = (TextView) findViewById(R.id.winddirec);
		mWindPower = (TextView) findViewById(R.id.windpower);
		mSunRise = (TextView) findViewById(R.id.sunrise);
		mSunSet = (TextView) findViewById(R.id.sunset);
		mHumidness = (TextView) findViewById(R.id.humidness);
		mBodyTemp = (TextView) findViewById(R.id.bodytemp);

		sportLayout = (LinearLayout) findViewById(R.id.sport_layout);
		sportLayout.setTag("sport");

		washcarLayout = (LinearLayout) findViewById(R.id.washcar_layout);
		washcarLayout.setTag("cw");
		uvlLayout = (LinearLayout) findViewById(R.id.uv_layout);
		uvlLayout.setTag("uv");
		fluLayout = (LinearLayout) findViewById(R.id.flu_layout);
		fluLayout.setTag("flu");
		spfLayout = (LinearLayout) findViewById(R.id.spf_layout);
		spfLayout.setTag("spf");
		traveLayout = (LinearLayout) findViewById(R.id.travel_layout);
		traveLayout.setTag("trav");
		clothLayout = (LinearLayout) findViewById(R.id.cloth_layout);
		clothLayout.setTag("drsg");
		comfortLayout = (LinearLayout) findViewById(R.id.comfort_layout);
		comfortLayout.setTag("comf");

		mWeatherTopVisable = (TextView) findViewById(R.id.weather_tmp_top_invisable);
		mWeatherTodayLayout = (LinearLayout) findViewById(R.id.weather_today_layout);
		mWeatherTop = (TextView) findViewById(R.id.weather_tmp_top);

		mPm10ViewBack = (TextView) findViewById(R.id.pm10_view_back);
		mPm25ViewBack = (TextView) findViewById(R.id.pm25_view_back);

		mDetailLayout = (LinearLayout) findViewById(R.id.detail_layout);
		mDetailImage = (TextView) findViewById(R.id.detail_image);
		mDetailTitle = (TextView) findViewById(R.id.detail_title);
		mDetailValue = (TextView) findViewById(R.id.detail_value);
		mDetailContent = (TextView) findViewById(R.id.detail_content);

		mScrollView = (SuperScrollView) findViewById(R.id.weather_scroll);
	}

	private void fillWeatherInfo(HeWeather heWeather) {
		if (heWeather != null) {

			List<HeWeatherData> heWeatherDatas = heWeather.getHeWeatherData();
			if (heWeatherDatas != null && heWeatherDatas.size() > 0) {

				HeWeatherData heWeatherData = heWeatherDatas.get(0);
				if (heWeatherData.getBasic() != null) {
					mWeatherCity.setText(heWeatherData.getBasic().getCity());
				}
				mWeatherStatus.setText(heWeatherData.getNow().getCond()
						.getTxt());
				String qlty = heWeatherData.getAqi().getCity().getQlty();
				mWeatherTmp.setText(heWeatherData.getNow().getTmp() + "°");
				mWeatherAirlevel.setText(heWeatherData.getAqi().getCity()
						.getPm25()
						+ " " + qlty);
				Drawable drawLeft = null;
				if (qlty.equals("良")) {
					drawLeft = getResources().getDrawable(R.drawable.leaf);
				}
				mWeatherAirlevel.setCompoundDrawables(drawLeft, null, null,
						null);

				for (int i = 0; i < heWeatherData.getDaily_forecast().size() - 1; i++) {
					TextView weatherDay = findView(i, "weather_day");
					TextView weatherImg = findView(i, "weather_img");
					TextView weatherTmp = findView(i, "weather_tmp");

					String date = getDate(heWeatherData, i);
					String condTxt = getCondText(heWeatherData, i);
					String maxAndMinTmp = getTmp(heWeatherData, i);
					weatherDay.setText(date);
					weatherTmp.setText(maxAndMinTmp);
					if (condTxt.equals("多云")) {
						weatherImg.setBackgroundResource(R.drawable.duoyun);
					} else if (condTxt.equals("晴")) {
						weatherImg.setBackgroundResource(R.drawable.sun);
					} else if (condTxt.contains("雨")) {
						weatherImg.setBackgroundResource(R.drawable.xiayu);
					}
				}
				mWeatherAirlevelTxt.setText(heWeatherData.getAqi().getCity()
						.getQlty());
				mWeatherAirlevelNum.setText(heWeatherData.getAqi().getCity()
						.getPm25());
				mWeatherPm25Txt.setText(heWeatherData.getAqi().getCity()
						.getPm25());

				int pm25 = Integer.parseInt(heWeatherData.getAqi().getCity()
						.getPm25());

				mWeatherPm25View.getLayoutParams().width = getPmWidth(pm25,
						mPm25ViewBack);
				mWeatherPm25View.requestLayout();
				// mWeatherPm25View.setWidth(getPmWidth(pm25, mPm25ViewBack));
				mWeatherPm10Txt.setText(heWeatherData.getAqi().getCity()
						.getPm10());
				int pm10 = Integer.parseInt(heWeatherData.getAqi().getCity()
						.getPm10());

				mWeatherPm10View.getLayoutParams().width = getPmWidth(pm10,
						mPm10ViewBack);
				mWeatherPm10View.requestLayout();
				// mWeatherPm10View.setWidth(getPmWidth(pm10, mPm10ViewBack));

				mJsonObject = (JSONObject) JSON.toJSON(heWeatherData
						.getSuggestion());

				mSportTxt.setText(heWeatherData.getSuggestion().getSport()
						.getBrf());
				mWashCarTxt.setText(heWeatherData.getSuggestion().getCw()
						.getBrf());
				mUVTxt.setText(heWeatherData.getSuggestion().getUv().getBrf());
				mShoppingTxt.setText(heWeatherData.getSuggestion().getFlu()
						.getBrf());
				mFishingTxt.setText(heWeatherData.getSuggestion().getTrav()
						.getBrf());
				mClothTxt.setText(heWeatherData.getSuggestion().getDrsg()
						.getBrf());
				mComfortTxt.setText(heWeatherData.getSuggestion().getComf()
						.getBrf());

				mWindDirec.setText(heWeatherData.getNow().getWind().getDir());
				mWindPower.setText(heWeatherData.getNow().getWind().getSc());
				mSunRise.setText(heWeatherData.getDaily_forecast().get(0)
						.getAstro().getSr());
				mSunSet.setText(heWeatherData.getDaily_forecast().get(0)
						.getAstro().getSs());
				mHumidness.setText(heWeatherData.getNow().getHum() + "%");
				mBodyTemp.setText(heWeatherData.getNow().getFl());

			}
		} else {

		}
	}

	private int getPmWidth(int pm, TextView textView) {
		return (int) (((float) pm / 200f) * textView.getWidth());
	}

	private String getTmp(HeWeatherData heWeatherData, int index) {
		return heWeatherData.getDaily_forecast().get(5).getTmp().getMax()
				+ "/"
				+ heWeatherData.getDaily_forecast().get(index).getTmp()
						.getMin();
	}

	private String getCondText(HeWeatherData heWeatherData, int index) {
		return heWeatherData.getDaily_forecast().get(index).getCond()
				.getTxt_d();
	}

	private String getDate(HeWeatherData heWeatherData, int index) {
		return heWeatherData.getDaily_forecast().get(index).getDate();
	}

	private TextView findView(int i, String name) {
		return (TextView) findViewById(getResources().getIdentifier(name + i,
				"id", "com.pei.weather"));
	}

	private void requestWeather() throws AuthFailureError {
		RequestQueue mQueue = Volley.newRequestQueue(this);
		StringRequest stringRequest = new StringRequest(Request.Method.GET,
				"http://apis.baidu.com/heweather/weather/free?city=beijing",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.v("TAG", response);

						mHeWeather = JSON
								.parseObject(response, HeWeather.class);
						Log.v("TAG", mHeWeather + "");
						Log.v("TAG", mHeWeather.getHeWeatherData().get(0)
								.getStatus());
						fillWeatherInfo(mHeWeather);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Apikey", "724fa564c796688c7b26e5b754f36e68");
				return headers;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Apikey", "724fa564c796688c7b26e5b754f36e68");
				return headers;
			}
		};

		mQueue.add(stringRequest);

	}

	@Override
	public void onScroll(int x, int y, int oldx, int oldy) {

	}

	@Override
	public void onScrollY(int scrollY) {
		if (scrollY >= mWeatherTopVisable.getHeight()) {
			mWeatherTop.setVisibility(View.VISIBLE);
		} else {
			mWeatherTop.setVisibility(View.GONE);
		}

	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			String tag = null;

			if (mDetailInfo == null) {
				mDetailInfo = new DetailInfo();
			}
			if (view.getTag() instanceof String) {
				tag = (String) view.getTag();
				packageDetail(mDetailInfo, tag);
				detailShow(mDetailInfo.getResId(), mDetailInfo.getTitle(),
						mDetailInfo.getValue(), mDetailInfo.getContent());
			}
		}
	};

	private void packageDetail(DetailInfo detailInfo, String tag) {
		if (tag.equals("sport")) {
			detailInfo.setResId(R.drawable.sport);
		} else if (tag.equals("cw")) {
			detailInfo.setResId(R.drawable.washcar);

		} else if (tag.equals("uv")) {
			detailInfo.setResId(R.drawable.glass);

		} else if (tag.equals("flu")) {
			detailInfo.setResId(R.drawable.flu);

		} else if (tag.equals("spf")) {
			detailInfo.setResId(R.drawable.umbrella);

		} else if (tag.equals("trav")) {
			detailInfo.setResId(R.drawable.trav);

		} else if (tag.equals("drsg")) {
			detailInfo.setResId(R.drawable.cloth);
		} else if (tag.equals("comf")) {
			detailInfo.setResId(R.drawable.coffee);
		}

		if (tag.equals("spf")) {
			detailInfo
					.setContent("属强紫外辐射天气，外出时应加强防护，建议涂擦SPF在15-20之间，PA++的防晒护肤品。");
			detailInfo.setValue("强");
		} else {
			JSONObject jsonObject = mJsonObject.getJSONObject(tag);
			detailInfo.setContent(jsonObject.getString("txt"));
			detailInfo.setValue(jsonObject.getString("brf"));
		}

		detailInfo.setTitle(mDetailMap.get(tag));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.detail_layout:
			mDetailLayout.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	private void detailShow(int resId, String title, String value,
			String content) {
		mDetailImage.setBackgroundResource(resId);
		mDetailTitle.setText(title);
		mDetailValue.setText("-" + value);
		mDetailContent.setText(content);
		mDetailLayout.setVisibility(View.VISIBLE);
	}

	private class DetailInfo {
		private int resId;
		private String title;
		private String value;
		private String content;

		public int getResId() {
			return resId;
		}

		public void setResId(int resId) {
			this.resId = resId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

	}

}
