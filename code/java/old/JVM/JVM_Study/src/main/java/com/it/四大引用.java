//package com.it;
//
//import com.it.双亲委派.Car;
//
//import java.lang.ref.SoftReference;
//
///**
// * @author 帅宏-coding
// * @Money java_offer_13k
// * @date 2025/2/23 星期日 14:44
// */
//public class 四大引用 {
//    public static void main(String[] args) {
//        Car demo = new Car();
//        SoftReference<Car> carSoftReference = new SoftReference<>(demo);
//        demo = null;
//        if(carSoftReference!=null){
//            System.out.println("temp is not null" + carSoftReference.get());
//        }else{
//            System.out.println("temp is null");
//             demo = new Car();
//             carSoftReference = new SoftReference<>(demo);
//        }
//    }
//}
