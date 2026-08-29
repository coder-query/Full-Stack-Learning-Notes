package com.it;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/23 星期日 19:25
 */
public class Finalize_Test {
    private static Finalize_Test save_test = null;
    public void alive(){
        System.out.println("我还存活着,没被GC回收");
    }
    @Override
    protected void finalize() throws Throwable {
        System.out.println("我自救一次");
        Finalize_Test.save_test = this;
    }
    private static  String name = "123";

    public static void main(String[] args) {
        save_test = new Finalize_Test();
        save_test = null;
//        Thread.sleep(1000);
        System.out.println(name);
        try {
            System.gc();
            Thread.sleep(500);
            if(save_test!=null){
                save_test.alive();
            }else{
                System.out.println("我第一次被GC回收了..");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        save_test = null;
        try {
            System.gc();
            Thread.sleep(1000);
            if(save_test!=null){
                save_test.alive();
            }else{
                System.out.println("我第二次被GC回收了..");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
