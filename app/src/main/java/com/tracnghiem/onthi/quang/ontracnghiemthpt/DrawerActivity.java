package com.tracnghiem.onthi.quang.ontracnghiemthpt;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.database.DatabseHelper;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monontap.OntapFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi.MonDiaLyFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi.MonGDCDFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi.MonSinhFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.monthi.MonSuFragment;
import com.tracnghiem.onthi.quang.ontracnghiemthpt.tintuc.TinTucFragment;

import java.io.IOException;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference  myRef = database.getReference("mesage");
        myRef.setValue("Hello, World");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new TinTucFragment()).commit();
        getSupportActionBar().setTitle("Tin Tuc");
        drawerLayout.closeDrawers();
        DatabseHelper databseHelper = new DatabseHelper(this);
        try {
            databseHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public  void LoadProgesbar(){
        dialog = new ProgressDialog(this);
        dialog.setMax(100);
        dialog.setMessage("Vui lòng chờ...");
        dialog.setTitle("Đang tải dữ liệu !...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (dialog.getProgress() <= dialog.getMax()) {
                        Thread.sleep(50);
                        handler.sendMessage(handler.obtainMessage());
                        if (dialog.getProgress() == dialog.getMax()) {
                            dialog.dismiss();
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
    public void LoadProgesbarHandle(){
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Đang tải dữ liệu...");
        progress.show();
        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                progress.cancel();

            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 1500);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(DrawerActivity.this);
            builder.setTitle("Thoát");
            builder.setMessage("Xác nhận thoát !");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dialog.incrementProgressBy(1);
        }
    };


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_khokienthuc) {
            LoadProgesbarHandle();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new OntapFragment()).commit();
            getSupportActionBar().setTitle("Kho Kiến Thức");
            drawerLayout.closeDrawers();
        }


        if (id == R.id.nav_toan) {
            LoadProgesbarHandle();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MonSuFragment()).commit();
            getSupportActionBar().setTitle("Môn Lịch Sử");
            drawerLayout.closeDrawers();

        }
        if (id == R.id.nav_dialy) {
            LoadProgesbarHandle();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MonDiaLyFragment()).commit();
            getSupportActionBar().setTitle("Môn Địa Lý");
            drawerLayout.closeDrawers();
        }
        if (id == R.id.nav_sinhhoc) {
            LoadProgesbarHandle();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MonSinhFragment()).commit();
            getSupportActionBar().setTitle("Môn Sinh Học");
            drawerLayout.closeDrawers();
        }
        if (id == R.id.nav_gdcd) {
            LoadProgesbarHandle();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MonGDCDFragment()).commit();
                getSupportActionBar().setTitle("Môn GDCD");
            drawerLayout.closeDrawers();
        }if (id == R.id.nav_thongke) {
            LoadProgesbarHandle();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new LichSuLamBaiFragment()).commit();
            getSupportActionBar().setTitle("Lịch Sử Làm Bài");
            drawerLayout.closeDrawers();
        }
        if (id==R.id.nav_tintuc){
            LoadProgesbarHandle();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new TinTucFragment()).commit();
            getSupportActionBar().setTitle("Tin Tuc");
            drawerLayout.closeDrawers();
        }
        if(id ==R.id.nav_thoat){
            AlertDialog.Builder builder = new AlertDialog.Builder(DrawerActivity.this);
            builder.setTitle("Thoát");
            builder.setMessage("Bạn có thực sự muốn thoát !");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
