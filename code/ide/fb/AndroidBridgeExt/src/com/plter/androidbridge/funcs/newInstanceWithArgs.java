/**
 * 
 * @author plter 
 * website http://plter.com http://plter.sinaapp.com
 * email xtiqin@163.com
 */


package com.plter.androidbridge.funcs;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.plter.androidbridge.AndroidBridgeArg;
import com.plter.androidbridge.lang.JavaClass;
import com.plter.androidbridge.lang.JavaObject;

public class newInstanceWithArgs implements FREFunction{
	
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		
		try {
			int joId = arg1[0].getAsInt();
			
			AndroidBridgeArg[] argsForSend = new AndroidBridgeArg[arg1.length-1];
			for (int i = 0; i < argsForSend.length; i++) {
				argsForSend[i] = AndroidBridgeArg.encodeJSONString(arg1[i+1].getAsString());
			}
			
			JavaObject jo = JavaObject.getJavaObject(joId);
			if (jo!=null&&jo instanceof JavaClass) {
				AndroidBridgeArg result = ((JavaClass)jo).newInstanceWithArgs(argsForSend);
				if (result!=null) {
					return FREObject.newObject(result.toJSONString());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}