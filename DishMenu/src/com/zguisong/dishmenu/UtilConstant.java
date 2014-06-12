package com.zguisong.dishmenu;
/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class UtilConstant {
	public static final int MENU_CATEGORY = 5;
	public static final int PHOTO_PICKED_WITH_DATA = 22;

    static String[] navigationText = {
        "推荐",
        "菜式",
        "我的菜单",
        "最受欢迎",
        "关于"
    };

    static int[] navigationImage = {
    	R.drawable.ic_navigation_recommend,R.drawable.ic_navigation_style,R.drawable.ic_navigation_finished,R.drawable.ic_navigation_hot,R.drawable.ic_navigation_connected
     };
    
    static String[] chefedDishText = {
        "泉水鱼",
        "香辣爆全腰",
        "茶王焗沙虾",
        "鲜紫苏鱼",
        "美味猪手"
    };
/*   
    static int[] chefedDishImage = {
    	R.drawable.fish,R.drawable.kidney,R.drawable.shrimp,R.drawable.sufish,R.drawable.trotter
     };
  */  
    static String[] dishStyle_SiChuanFood = {
        "泉水鱼",
        "香辣爆全腰",
        "茶王焗沙虾",
        "鲜紫苏鱼",
        "美味猪手"
    };
/*    
    static int[] siChuanFoodImage = {
    	R.drawable.fish,R.drawable.kidney,R.drawable.shrimp,R.drawable.sufish,R.drawable.trotter
     };
     */
    static String[] dishStyle_Drink = {
        "sangria水果酒",
        "果味饮料",
        "红酒1",
        "卡布奇诺",
        "青柠冰水"
    };
    /*
    static int[] drinkImage = {
    	R.drawable.sangria,R.drawable.fruitdrinks,R.drawable.redwine,R.drawable.cappuccino,R.drawable.lemonjuice
     };
     */
    static String[] dishStyleText = {
        "东北菜",
        "川菜",
        "海鲜",
        "甜品",
        "主食",
        "酒水"
    };
    public static final String[] SITUATION = new String[] {
		"http://b164.photo.store.qq.com/psb?/V10b9Bw14dCzE0/9mK0bUWeKgQU94egrBnaRKLQhI3U9Y.MPpwVsvVf6ps!/b/dOsaxWFCCAAA&amp;bo=FQIgAwAAAAABABM!&rf=photoDetail",
		"http://b163.photo.store.qq.com/psb?/V10b9Bw14dCzE0/NTv5yF0xu3ym0L7RmwL3nEKjJYhDCQgDdXIk2v8WtqY!/b/dEh*KWEyHAAA&amp;bo=IANYAgAAAAABAF4!&rf=photoDetail",
		"http://b163.photo.store.qq.com/psb?/V10b9Bw14dCzE0/.zC1Z9rW5znYO0roWj9ialILRFke4Olksnv5HDCDUas!/b/dHEqN2HmHwAA&amp;bo=IANYAgAAAAABAF4!&rf=photoDetail",
		"http://b166.photo.store.qq.com/psb?/V10b9Bw14dCzE0/cOLfk5fanPlBTgScvD2HPU7qeVIZbfRFBzMNldWQpuo!/b/dJn5AGPYBgAA&amp;bo=IANYAgAAAAABAF4!&rf=photoDetail",
		"http://b166.photo.store.qq.com/psb?/V10b9Bw14dCzE0/YOPs8MbUyRpn7qgIr.ruI7WIG9FhhIQK0hpBygRxcGY!/b/dF9Z*GJMBgAA&amp;bo=IANYAgAAAAABAF4!&rf=photoDetail",
		"http://b165.photo.store.qq.com/psb?/V10b9Bw14dCzE0/opBR300zdzY6a75C1iuJJH1.HFUdbUW*AA2nFWsVyaQ!/b/dPyaWmKXBwAA&amp;bo=WAIgAwAAAAABAF4!&rf=photoDetail",
		"http://b166.photo.store.qq.com/psb?/V10b9Bw14dCzE0/5GkUd81q2FFJ5scErEsFwc7uFF2X*M7UHCZo9Mw0enQ!/b/dF9Z*GJRBgAA&amp;bo=IAN8AgAAAAABAHo!&rf=photoDetail",
		"http://b164.photo.store.qq.com/psb?/V10b9Bw14dCzE0/l4MFUsfhjfu68hqDrvtpILD9*BmxPqTWNBD0*l518A0!/b/dAmhxmGZCgAA&amp;bo=IAN8AgAAAAABAHo!&rf=photoDetail",
		"http://b165.photo.store.qq.com/psb?/V10b9Bw14dCzE0/rSZpkjAM8AK8aNfPerqga9TfWCXDhvxegfVSZ3j5qoo!/b/dBmkYGJZBwAA&amp;bo=IAN8AgAAAAABAHo!&rf=photoDetail",
		"http://b166.photo.store.qq.com/psb?/V10b9Bw14dCzE0/nECLZSEkyQhtsTRCkAb3wvaXkb8EAKESW9x3K8EYB0U!/b/dGlU*GKhBgAA&amp;bo=IAN8AgAAAAABAHo!&rf=photoDetail",
		"http://b166.photo.store.qq.com/psb?/V10b9Bw14dCzE0/N55BNdgsPT6i2GSkGbgFqFtfQTylG2DTsCNIUqGO6Kg!/b/dNnkAGOYBQAA&amp;bo=IAN8AgAAAAABAHo!&rf=photoDetail",
		"http://b166.photo.store.qq.com/psb?/V10b9Bw14dCzE0/u*aGeERqB2Ok7w4tzcYvRX6r7HHk302h8nyt.AxzS98!/b/dKjuAGOmBAAA&amp;bo=IAN8AgAAAAABAHo!&rf=photoDetail",
		"http://b166.photo.store.qq.com/psb?/V10b9Bw14dCzE0/PYE5dRIl5qYpLD3Z.NE13UGEAlaEv9CyOFKNVNO2iJw!/b/dEHh*WKnBAAA&amp;bo=WAIgAwAAAAABAF4!&rf=photoDetail&rf=photoDetail",
		"http://b164.photo.store.qq.com/psb?/V10b9Bw14dCzE0/AnODSxZvI4OeKqw4d9p0AM6.NtksIEzTqKrSXNCQu7g!/b/dB62z2E6CAAA&amp;bo=fAIgAwAAAAABAHo!&rf=photoDetail",
		"http://b165.photo.store.qq.com/psb?/V10b9Bw14dCzE0/n0GqzpT48FTYLcgk0WXdzTYEd5caZSTZMhuIB9sq4wM!/b/dMGnWmL2BgAA&amp;bo=IAN8AgAAAAABAHo!&rf=photoDetail",
		"http://b163.photo.store.qq.com/psb?/V10b9Bw14dCzE0/LS6B*ctItTJWQSrjXcXz.Y4FzCtn1KaJ57A8J096r10!/b/dKKVMmHDHAAA&amp;bo=IAMVAgAAAAABABM!&rf=photoDetail",
		"http://b166.photo.store.qq.com/psb?/V10b9Bw14dCzE0/tRRg3vdt5rCTrm7vjITzgCGmQcKiCc.5fzViifDrc7U!/b/dBLS.mImBQAA&amp;bo=IAMVAgAAAAABABM!&rf=photoDetail",
		"http://b164.photo.store.qq.com/psb?/V10b9Bw14dCzE0/iA537kLauMIenZe*NGZu0a5uCJooYpdK19DSpRcb1l8!/b/dKwixWFsBgAA&amp;bo=IAN8AgAAAAABAHo!&rf=photoDetail",
		"http://b163.photo.store.qq.com/psb?/V10b9Bw14dCzE0/M5i2f2fxbPOJRV18AXRMRHgjprVWvcnIpfmg838d7Ok!/b/dKGzNWEOHQAA&amp;bo=IAN8AgAAAAABAHo!&rf=photoDetail",
		"http://b163.photo.store.qq.com/psb?/V10b9Bw14dCzE0/vmANi6wMW*xKaOJa8cRlZdINWI85aZ0ai5VXJaueAqA!/b/dAcBMWGNIAAA&amp;bo=fAIgAwAAAAABAHo!&rf=photoDetail&rf=photoDetail",
};
}
