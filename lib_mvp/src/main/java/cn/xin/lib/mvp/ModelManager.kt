package cn.xin.lib.mvp

import java.lang.reflect.Constructor
import java.lang.reflect.InvocationTargetException
import java.util.concurrent.ConcurrentHashMap

/**
 * @author DELL
 * @date 2018年8月29日 029
 * E-Mail:n.zjx@163.com
 * SimpleMvp
 * ModelManager: Model 创建管理
 */
object ModelManager {

    private val DATA_CACHE: ConcurrentHashMap<Class<out IBaseModel>, IBaseModel> =
        ConcurrentHashMap(8)

    /**
     * 创建获取 Model 层实例
     *
     * @param clazz IBaseModel 子类 class
     */
    @Suppress("UNCHECKED_CAST")
    fun <M : IBaseModel> create(clazz: Class<M>): M {

        var model = DATA_CACHE[clazz]
        if (model != null) {
            return model as M
        }

        synchronized(DATA_CACHE) {
            model = DATA_CACHE[clazz]
            if (model == null) {
                val constructor = clazz.getDeclaredConstructor()
                constructor.isAccessible = true
                model = constructor.newInstance()
                DATA_CACHE[clazz] = model as M
            }
        }

        return model as M
    }

}
