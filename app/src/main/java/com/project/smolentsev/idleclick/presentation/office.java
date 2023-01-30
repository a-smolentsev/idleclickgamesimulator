package com.project.smolentsev.idleclick.presentation;

import static android.util.Log.d;



/*
public class office extends AppCompatActivity {
    private final String fileName = "data.txt";
 //   Data data;
    private TextView textView;
    private long setCoins;
    private boolean flagThread;
    private ImageView build;
    private TextView cost;
    private TextView textbuild;
    private ImageButton btn_build;
    private final loadData loadata = new loadData();
    private final saveData savedata = new saveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);
        data = loadata.loadJson(getApplicationContext());
        build = findViewById(R.id.build);
        flagThread = true;

        d("RelaxCreateOffice", String.valueOf(data.getRelax()));
        textView = findViewById(R.id.coins_number);
        initCoinValueText();
        coinsRefresher();
        cost = findViewById(R.id.cost);
        textbuild = findViewById(R.id.textOffice);
        btn_build = findViewById(R.id.btn_build);

        switch ((int) data.getBuildOffice()) {
            case (0):
                //  ch1.setImageDrawable(resizeImage(R.drawable.chr_1));
                build.setVisibility(View.INVISIBLE);
                cost.setText(getString(R.string.cost, 250000));
                break;
            case (1):
                build.setImageResource(R.drawable.build1);
                cost.setText(getString(R.string.cost, data.getBuildOffice() * 500000));
                break;
            case (2):
                build.setImageResource(R.drawable.build2);
                cost.setText(getString(R.string.cost, data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000)));
                break;
            case (3):
                build.setImageResource(R.drawable.build3);
                cost.setText(getString(R.string.cost, data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000)));
                break;
            case (4):
                build.setImageResource(R.drawable.build4);
                cost.setText(getString(R.string.cost, data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000)));
                break;
            case (5):
                build.setImageResource(R.drawable.build5);

                break;
        }

        btn_build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch ((int) data.getBuildOffice()) {
                    case (0):
                        if (data.getCoinsValue() >= 250000) {
                            data.setCoinsValue(data.getCoinsValue() - 250000);
                            data.setBuildOffice(data.getBuildOffice() + 1);
                            build.setImageResource(R.drawable.build1);
                            build.setVisibility(View.VISIBLE);
                            Toast.makeText(office.this, R.string.office, Toast.LENGTH_SHORT).show();

                            cost.setText(getString(R.string.cost, 500000));
                          //  savedata.saveJson(getApplicationContext(), data);
                        } else {
                            Toast.makeText(office.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();

                        }


                        break;
                    case (1):
                        if (data.getCoinsValue() >= data.getBuildOffice() * 500000) {
                            data.setCoinsValue(data.getCoinsValue() - data.getBuildOffice() * 500000);
                            data.setBuildOffice(data.getBuildOffice() + 1);
                            build.setImageResource(R.drawable.build2);
                            build.setVisibility(View.VISIBLE);
                            build.invalidate();
                            Toast.makeText(office.this, R.string.office, Toast.LENGTH_SHORT).show();
                            cost.setText(getString(R.string.cost, data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000)));
                           // savedata.saveJson(getApplicationContext(), data);

                        } else {
                            Toast.makeText(office.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();

                        }

                        break;
                    case (2):
                        if (data.getCoinsValue() >= data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000)) {
                            data.setCoinsValue(data.getCoinsValue() - data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000));
                            data.setBuildOffice(data.getBuildOffice() + 1);
                            build.setImageResource(R.drawable.build3);
                            build.setVisibility(View.VISIBLE);
                            build.invalidate();
                            Toast.makeText(office.this, R.string.office, Toast.LENGTH_SHORT).show();
                            cost.setText(getString(R.string.cost, data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000)));
                           // savedata.saveJson(getApplicationContext(), data);

                        } else {
                            Toast.makeText(office.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();

                        }


                        break;
                    case (3):
                        if (data.getCoinsValue() >= data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000)) {
                            data.setCoinsValue(data.getCoinsValue() - data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000));
                            data.setBuildOffice(data.getBuildOffice() + 1);
                            build.setImageResource(R.drawable.build4);
                            build.setVisibility(View.VISIBLE);
                            build.invalidate();
                            cost.setText(getString(R.string.cost, data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000)));
                            Toast.makeText(office.this, R.string.office, Toast.LENGTH_SHORT).show();

                            //  savedata.saveJson(getApplicationContext(), data);
                        } else {
                            Toast.makeText(office.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();

                        }


                        break;
                    case (4):
                        if (data.getCoinsValue() >= data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000)) {
                            data.setCoinsValue(data.getCoinsValue() - data.getBuildOffice() * ((data.getBuildOffice() - 1) * 500000));
                            data.setBuildOffice(data.getBuildOffice() + 1);
                            build.setImageResource(R.drawable.build5);
                            build.setVisibility(View.VISIBLE);
                            build.invalidate();
                            Toast.makeText(office.this, R.string.office, Toast.LENGTH_SHORT).show();
                            cost.setText(getString(R.string.cost, data.getBuildOffice() * ((data.getBuildOffice() - 1) * 1000000)));
                        //    savedata.saveJson(getApplicationContext(), data);

                        } else {
                            Toast.makeText(office.this, R.string.not_enough_money, Toast.LENGTH_SHORT).show();

                        }


                        break;

                }


            }
        });

    }

    private void coinsRefresher() {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                {
                    long coinsValue = data.getCoinsValue();
                    // String text;

                    if (coinsValue < data.getMaxCoinsValue())
                        textView.setText(textView.getContext().getResources().getString(R.string.coins, coinsValue));
                    else
                        textView.setText(textView.getContext().getResources().getString(R.string.rich));


                    mHandler.postDelayed(this, 100);
                }


            }
        };
        mHandler.post(runnable);
    }


    private void initCoinValueText() {
        // String text="Coins: "+data.getCoinsValue();
        TextView textView = findViewById(R.id.coins_number);

        textView.setText(textView.getContext().getResources().getString(R.string.coins, data.getCoinsValue()));
    }


    private void threads() {
        //<editor-fold desc="piggyRunnable">
        Runnable piggyRunnable = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    while (flagThread) {
                        long coinsValue = data.getCoinsValue();

                        if (coinsValue < data.getMaxCoinsValue()) {
                            if (data.getRelax() < 50 && data.getRelax() > 1) {
                                data.setCoinsValue(coinsValue
                                        + data.getGreedyPiggyNum()
                                        + 5 * data.getLittleTommyNum()
                                        + 25 * data.getBusinessPackNum()
                                        + 80 * data.getSlyMarioNum());
                            } else {
                                data.setCoinsValue(coinsValue
                                        + data.getGreedyPiggyNum()
                                        + 10 * data.getLittleTommyNum()
                                        + 50 * data.getBusinessPackNum()
                                        + 200 * data.getSlyMarioNum());
                            }
                            if (data.getRelax() == 0) {
                                data.setCoinsValue(coinsValue);
                            }
                            try {
                                wait(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else
                            data.setCoinsValue(data.getMaxCoinsValue());

                    }
                }
            }
        };
        //</editor-fold>

        Thread piggyThread = new Thread(piggyRunnable);
        piggyThread.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        final String TAG = "ACTIVITY_STATE2";
        flagThread = false;
       // savedata.saveJson(getApplicationContext(), data);
        d("RelaxOfficeStop", String.valueOf(data.getRelax()));

    }

    @Override
    public void onStart() {
        super.onStart();
        data = loadata.loadJson(getApplicationContext());

        threads();
        coinsRefresher();
        d("RelaxStartOffice", String.valueOf(data.getRelax()));
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(office.this, PlayActivity.class);
        startActivity(i);
        // flagThread = false;
        final long TAG = data.getCoinsValue();
        savedata.saveJson(getApplicationContext(), data);
        finish();

    }


} */
