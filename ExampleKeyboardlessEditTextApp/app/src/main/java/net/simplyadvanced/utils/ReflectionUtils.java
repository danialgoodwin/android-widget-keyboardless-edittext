package net.simplyadvanced.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;

/** Static helper methods for using reflection. This was inspired from:
 * https://code.google.com/p/csipsimple/source/browse/trunk/ActionBarSherlock/src/com/actionbarsherlock/internal/utils/UtilityWrapper.java?r=1741
 */
public class ReflectionUtils {
	private ReflectionUtils() {}

	/**
     * Returns method if available in class or superclass (recursively),
     * otherwise returns null.
     */
    public static Method getMethod(Class<?> cls, String methodName, Class<?>... parametersType) {
        Class<?> sCls = cls.getSuperclass();
        while (sCls != Object.class) {
            try {
                return sCls.getDeclaredMethod(methodName, parametersType);
            } catch (NoSuchMethodException e) {
                // Just super it again
            }
            sCls = sCls.getSuperclass();
        }
        return null;
//        throw new RuntimeException("Method not found " + methodName);
    }
    
    /** Returns results if available, otherwise returns null. */
    public static Object invokeMethod(Method method, Object receiver, Object... args) {
        try {
            return method.invoke(receiver, args);
        } catch (IllegalArgumentException e) {
            Log.e("Safe invoke fail", "Invalid args", e);
        } catch (IllegalAccessException e) {
            Log.e("Safe invoke fail", "Invalid access", e);
        } catch (InvocationTargetException e) {
            Log.e("Safe invoke fail", "Invalid target", e);
        }
        
        return null;
    }

}
