package com.bw.movie;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bw.movie.model.bean.MovieByCinemaIdBean;
import com.bw.movie.view.adapter.MyMovieByCinemaIdCoverAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gyf.barlibrary.ImmersionBar;
import com.bw.movie.model.bean.CinemaCommentBean;
import com.bw.movie.model.bean.CinemaDetailPop;
import com.bw.movie.model.bean.CinemawhentimeBean;
import com.bw.movie.presenter.WhenCinemaPlayPresenter;
import com.bw.movie.view.IView.WhenCinemaPlayView;
import com.bw.movie.view.adapter.MyCoverAdapter;
import com.bw.movie.view.adapter.RecyclerCommentAdapter;
import com.bw.movie.view.adapter.WhenTimeAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

public class CinemaDetailActivity extends AppCompatActivity implements WhenCinemaPlayView {
    @BindView(R.id.tv_detailcinema_name)
    TextView tvDetailcinemaName;
    @BindView(R.id.detailcinema_topphoto)
    RecyclerCoverFlow detailcinemaTopphoto;
    @BindView(R.id.simp_detailcinema)
    SimpleDraweeView simpDetailcinema;
    @BindView(R.id.tv_detailcinema_address)
    TextView tvDetailcinemaAddress;
    @BindView(R.id.img_go)
    ImageView imgGo;
    @BindView(R.id.recycler_whenplay)
    RecyclerView recyclerWhenplay;
    private WhenCinemaPlayPresenter whenCinemaPlayPresenter;
    private String cinemasId;
    private String name;
    private String address;
    private String logo;
    private String movieId;
    private RadioButton rb_cinemadetail;
    private RadioButton rb_cinemacomment;
    private RecyclerView recycler_detailpop;
    private LinearLayout ll_commentall;
    private RadioGroup radio_cinemadetailpop;
    private CinemaDetailPop.ResultBean result;
    private TextView cinema_detail_location;
    private TextView cinema_detail_tel;
    private TextView cinema_detail_bus;
    private PopupWindow window;
    private ImageView img_down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        whenCinemaPlayPresenter = new WhenCinemaPlayPresenter();
        whenCinemaPlayPresenter.attachView(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pop_detail, null);
        window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        window.setBackgroundDrawable(dw);
        window.setAnimationStyle(R.style.PopupAnimation);
        img_down = view.findViewById(R.id.img_down);
        rb_cinemadetail = view.findViewById(R.id.rb_cinemapopdetail);
        rb_cinemacomment = view.findViewById(R.id.rb_cinemapopcomment);
        recycler_detailpop = view.findViewById(R.id.recycler_detailpop);
        ll_commentall = view.findViewById(R.id.ll_commentall);
        radio_cinemadetailpop = view.findViewById(R.id.radio_cinemadetailpop);
        cinema_detail_location = view.findViewById(R.id.cinema_details_location);
        cinema_detail_tel = view.findViewById(R.id.cinema_details_tel);
        cinema_detail_bus = view.findViewById(R.id.cinema_details_bus);



        Intent intent = getIntent();
        logo = intent.getStringExtra("logo");
        cinemasId = intent.getStringExtra("cinemasId");
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        whenCinemaPlayPresenter.getCinemaDetailPopData(cinemasId);
        whenCinemaPlayPresenter.getWhenCinemaPlayData(cinemasId,movieId);
        whenCinemaPlayPresenter.getCinemaCommentData(cinemasId,String.valueOf(1),String.valueOf(10));
        whenCinemaPlayPresenter.getMovieByCinemaId(cinemasId);
        Log.i("cinemasId",cinemasId+"");
        Log.i("movieId",movieId+"");

        tvDetailcinemaName.setText(name);
        simpDetailcinema.setImageURI(logo);
        tvDetailcinemaAddress.setText(address);


        imgGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });

    }

    private void showPopwindow() {
        window.showAtLocation(CinemaDetailActivity.this.findViewById(R.id.img_go),
                Gravity.BOTTOM, 0, 0);
        recycler_detailpop.setVisibility(View.GONE);
        ll_commentall.setVisibility(View.VISIBLE);
        radio_cinemadetailpop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==rb_cinemadetail.getId()){
                    recycler_detailpop.setVisibility(View.GONE);
                    ll_commentall.setVisibility(View.VISIBLE);
                }else if(checkedId==rb_cinemacomment.getId()){
                    ll_commentall.setVisibility(View.GONE);
                    recycler_detailpop.setVisibility(View.VISIBLE);
                }
            }
        });
        img_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        cinema_detail_location.setText(result.getAddress());
        cinema_detail_tel.setText(result.getPhone());
        cinema_detail_bus.setText(result.getVehicleRoute());


    }

    @Override
    public void whenCinemaSuccess(CinemawhentimeBean cinemawhentimeBean) {
      //   Toast.makeText(this, "cinemawhentimeBean.getResult():" + cinemawhentimeBean.getResult(), Toast.LENGTH_SHORT).show();
        recyclerWhenplay.setLayoutManager(new LinearLayoutManager(CinemaDetailActivity.this,LinearLayoutManager.VERTICAL,false));
        WhenTimeAdapter whenTimeAdapter = new WhenTimeAdapter(CinemaDetailActivity.this,cinemawhentimeBean.getResult());
        recyclerWhenplay.setAdapter(whenTimeAdapter);
        whenTimeAdapter.setOnItemClickListener(new WhenTimeAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent=new Intent(CinemaDetailActivity.this,BuyTicketActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void error() {

    }

    @Override
    public void movieSuccess(final MovieByCinemaIdBean result) {
        //Toast.makeText(this, "result:" + result, Toast.LENGTH_SHORT).show();
        detailcinemaTopphoto.setAdapter(new MyMovieByCinemaIdCoverAdapter(CinemaDetailActivity.this, result.getResult()));
        detailcinemaTopphoto.scrollToPosition(2);
        detailcinemaTopphoto.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                movieId = result.getResult().get(position).getId();
                whenCinemaPlayPresenter.getWhenCinemaPlayData(cinemasId, movieId);
            }
        });
    }

    @Override
    public void movieerror() {

    }

    @Override
    public void cinemaDetailPopsuccess(CinemaDetailPop cinemaDetailPop) {
        //Toast.makeText(this, "cinemaDetailPop.getResult():" + cinemaDetailPop.getResult(), Toast.LENGTH_SHORT).show();
        Log.i("hashmap",cinemaDetailPop.getResult()+"");
        result = cinemaDetailPop.getResult();
    }

    @Override
    public void cinemaerror() {

    }

    @Override
    public void cinemaCommetSuccess(CinemaCommentBean cinemaCommentBean) {
       // Toast.makeText(this, "cinemaCommentBean:" + cinemaCommentBean.getResult(), Toast.LENGTH_SHORT).show();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CinemaDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        recycler_detailpop.setLayoutManager(linearLayoutManager);
        RecyclerCommentAdapter recyclerCommentAdapter = new RecyclerCommentAdapter(CinemaDetailActivity.this,cinemaCommentBean.getResult());
        recycler_detailpop.setAdapter(recyclerCommentAdapter);
    }

    @Override
    public void cinemacommenterror() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        whenCinemaPlayPresenter.detachView();
    }
}
