package com.aserbao.aserbaosandroid.aaThird.rxJava3.api;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aserbao.aserbaosandroid.aaThird.rxJava3.download.AndroidScheduler;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Supplier;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

import static com.aserbao.aserbaosandroid.comon.commonData.StaticFinalValues.LOGO_FROM_ASERBAO;

public class RxJava3ApiActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "RxJava3ApiActivity";

    public static final int USE_FLOWABLE = 100;
    public static final int USE_OBSERVABLE = 101;
    public static final int USE_SINGLE = 102;
    public static final int USE_COMPLETABLE = 103;
    public static final int USE_MAYBE = 104;
    public static final int USE_CREATING_CREATE  = 0;
    public static final int USE_CREATING_DEFER = 10;
    public static final int USE_CREATING_DEFER_SAMPLE = 1001;
    public static final int USE_CREATING_EMPTY = 12;
    public static final int USE_CREATING_NEVER = 13;
    public static final int USE_CREATING_FROM_ARRAY = 15;
    public static final int USE_CREATING_FROM_ITERABLE = 150;
    public static final int USE_CREATING_JUST = 16;
    public static final int USE_CREATING_INTERVAL = 17;
    public static final int USE_CREATING_RANGE = 18;
    public static final int USE_CREATING_INTERVAL_RANGE = 19;
    public static final int USE_CREATING_REPEAT = 20;
    public static final int USE_CREATING_TIMER = 21;


    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"Rxjava中的被观察者"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的使用",USE_FLOWABLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Observable的使用",USE_OBSERVABLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Single的使用",USE_SINGLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Completable的使用",USE_COMPLETABLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Maybe的使用",USE_MAYBE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"创建操作符的使用"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的create创建",USE_CREATING_CREATE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Flowable 的defer方法",USE_CREATING_DEFER));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用defer创建被观察者 注意常按和点击的输出",USE_CREATING_DEFER_SAMPLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用empty创建被观察者例子",USE_CREATING_EMPTY));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用never创建被观察者例子",USE_CREATING_NEVER));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用fromArray", USE_CREATING_FROM_ARRAY));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用fromIterable", USE_CREATING_FROM_ITERABLE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用just", USE_CREATING_JUST));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用interval",USE_CREATING_INTERVAL));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用range",USE_CREATING_RANGE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用intervalRange",USE_CREATING_INTERVAL_RANGE));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用repeat",USE_CREATING_REPEAT));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("使用timer",USE_CREATING_TIMER));
        mBaseRecyclerBeen.add(new BaseRecyclerBean(StaticFinalValues.VIEW_HOLDER_HEAD,"转换操作符的使用"));







    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case USE_FLOWABLE:
            case USE_OBSERVABLE:
            case USE_SINGLE:
            case USE_COMPLETABLE:
            case USE_MAYBE:
                useObservable(position);
                break;
            case USE_CREATING_CREATE:
            case USE_CREATING_DEFER:
            case USE_CREATING_EMPTY:
            case USE_CREATING_NEVER:
            case USE_CREATING_FROM_ARRAY:
            case USE_CREATING_FROM_ITERABLE:
            case USE_CREATING_JUST:
            case USE_CREATING_INTERVAL:
            case USE_CREATING_RANGE:
            case USE_CREATING_INTERVAL_RANGE:
            case USE_CREATING_REPEAT:
            case USE_CREATING_TIMER:
                createObservable(position);
                break;
            case USE_CREATING_DEFER_SAMPLE:
                useDeferSample(isLongClick);
                break;

        }
    }

    /**
     * 测试defer的实例
     * @param isUseDefer 是否使用defer
     */
    private void useDeferSample(boolean isUseDefer) {
        RxJavaBean rxJavaBean = new RxJavaBean();
        rxJavaBean.mUseDef = isUseDefer;
        Observable<String> observable = rxJavaBean.valueObservable();
        rxJavaBean.setValues("change_value");
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void useObservable(int type) {
        switch (type){
            case USE_FLOWABLE:
                Flowable.just("Hello world").subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: "+ s + " ThreadName = " + Thread.currentThread());
                    }
                });
                break;
            case USE_OBSERVABLE:
                Observable.just("Hello world").subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: "+ s + " ThreadName = " + Thread.currentThread());
                    }
                });
                break;
            case USE_SINGLE:
                Single.create(new SingleOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
//                emitter.onSuccess(10);
//                emitter.onSuccess(100);//只能提交一个，多个无效。
                        emitter.onError(new Exception("error info"));//只能提交一个，多个无效。
                    }
                }).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidScheduler.mainThread())
                    .subscribe(new SingleObserver<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onSuccess(Integer integer) {
                            Log.e(TAG, "onSuccess: " + integer);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " + e.toString());
                        }
                    });
                break;
            case USE_COMPLETABLE:
                Observable<Integer> integerObservable = Completable.create(new CompletableOnSubscribe() {
                    @Override
                    public void subscribe(CompletableEmitter emitter) throws Exception {
                        emitter.onComplete();
//                emitter.onError(new Exception("error info"));
                    }
                }).andThen(Observable.range(1, 10));
                integerObservable
                    .delay(1,TimeUnit.SECONDS)
                    .subscribe(new Observer<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e(TAG, "onSubscribe: " + d );
                        }

                        @Override
                        public void onNext(Integer integer) {
                            Log.e(TAG, "onNext: " + integer );
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " +e );
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: " );
                        }
                    });
                break;
            case USE_MAYBE:
                Maybe.create(new MaybeOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(MaybeEmitter<Integer> emitter) throws Exception {
                /*for (int i = 0; i < 100; i++) {
                    emitter.onSuccess(i);
                }*/
                        emitter.onComplete();
//                emitter.onError(new Exception("error info"));
                    }
                }).subscribeWith(new DisposableMaybeObserver<Integer>() {
                    @Override
                    public void onStart() {
                        Log.e(TAG, "onStart: " );
                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Log.e(TAG, "onSuccess: " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
                break;
        }
    }


    /**
     * 创建被观察者的方法
     * @param type
     */
    private void createObservable(int type){
        switch (type){
            case USE_CREATING_CREATE:
                Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        Log.e(TAG, "get: 初始化 Observable");
                        emitter.onNext(LOGO_FROM_ASERBAO);
                    }
                });
                Log.e(TAG, "开始订阅第一个观察者 ");
                observable.subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: 第一个观察者 收到的结果为：" + s );
                    }
                }).dispose();
                Log.e(TAG, "开始订阅第二个观察者 ");
                observable.subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: 第二个观察者 收到的结果为：" + s );
                        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case USE_CREATING_DEFER:
                Observable<String> defer = Observable.defer(new Supplier<ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> get() throws Throwable {
                        Log.e(TAG, "get: 初始化 Observable");
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                Log.e(TAG, "subscribe: 发个消息" );
                                emitter.onNext(LOGO_FROM_ASERBAO);
                            }
                        });
                    }
                });
                Log.e(TAG, "开始订阅第一个观察者 ");
                defer.subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: 第一个观察者 收到的结果为：" + s );
                    }
                });
                Log.e(TAG, "开始订阅第二个观察者 ");
                defer.subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "accept: 第一个观察者 收到的结果为：" + s );
                    }
                });
                break;
            case USE_CREATING_EMPTY:
                //会发送一个null数据
                Observable<Integer> empty = Observable.empty();
                empty.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "empty onSubscribe: " );
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "empty onNext: " );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "empty onError: " );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "empty onComplete: " );
                    }
                });
                break;
            case USE_CREATING_NEVER:
                //不会发送任何消息，
                Observable<Integer> never = Observable.never();
                never.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "never onSubscribe: " );
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "never onNext: " );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "never onError: " );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "never onComplete: " );
                    }
                });
                break;

            case USE_CREATING_FROM_ARRAY:
                List<String> arrayList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    String value = String.valueOf(i);
                    arrayList.add(value);
                }
                Observable.fromArray(arrayList)
                    .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Throwable {
                        for (String string : strings) {
                            Log.e(TAG, "accept: " + string);
                        }
                    }
                });

                String[] array = {"a","b","c"};
                Observable.fromArray(array)
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(String s) {
                            Log.e(TAG, "onNext: " +s );
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: " );
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: " );
                        }
                    });

                break;

            case USE_CREATING_FROM_ITERABLE:
                List<String> list = new ArrayList<>();
                for (int i = 1; i < 10; i++) {
                    list.add( "第"+ String.valueOf(i) + "个");
                }
                Flowable.fromIterable(list).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Log.e(TAG, "iterableFlowable accept: " + s );
                    }
                });
                break;
            case USE_CREATING_JUST:
                // 创建被观察者，并发送just里面的每个参数。
                Observable.just(1,2,3,4).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.e(TAG, "accept: " + integer );
                    }
                });
                break;
            case USE_CREATING_INTERVAL:
                Observable.interval(3, 1, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Throwable {
                        Log.e(TAG, "intervalFloawble accept: " + aLong );
                    }
                });
                break;
            case USE_CREATING_RANGE:
                Observable.range(100,10).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.e(TAG, "accept: "+integer);
                    }
                });
                break;
            case USE_CREATING_INTERVAL_RANGE:
                Observable.intervalRange(100,10,3,1,TimeUnit.SECONDS)
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Throwable {
                            Log.e(TAG, "intervalRangeFlowable accept: " + aLong );
                        }
                    });
                break;
            case USE_CREATING_REPEAT:
                //create创建的Observable对象设置repeat次数无效
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                    }
                }).repeat(repeatTime)
                    .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        Log.e(TAG, repeatTime + "create accept: " + integer );
                    }
                });

                Observable.intervalRange(100,10,3,1,TimeUnit.SECONDS)
                    .repeat(repeatTime)
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Throwable {
                            Log.e(TAG, repeatTime + "intervalRangeFlowable accept: " + aLong );
                        }
                    });
                //just创建的Observable对象有repeat次数限制
                Observable.just(1)
                    .repeat(repeatTime)
                    .subscribe(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Throwable {
                            Log.e(TAG,  repeatTime + "just accept: " + integer );
                        }
                    });
                repeatTime ++;
                break;
            case USE_CREATING_TIMER:
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Throwable {
                            Log.e(TAG, "timerFlowable accept: " + aLong );
                        }
                    });
                break;
        }

    }
    int repeatTime = 1;


}