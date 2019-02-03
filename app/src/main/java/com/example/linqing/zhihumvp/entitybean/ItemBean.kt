package com.example.linqing.zhihumvp.entitybean

class ItemBean {

    /**
     * date : 20181223
     * stories : [{"images":["https://pic1.zhimg.com/v2-9bf376382c632d7c928d911b43aa46f4.jpg"],"type":0,"id":9704221,"ga_prefix":"122316","title":"不会用「丹田气」，也不敢唱调太高的的歌，怎么办？"},{"images":["https://pic2.zhimg.com/v2-3952429277f22855bb35ed6dadb5b701.jpg"],"type":0,"id":9704037,"ga_prefix":"122312","title":"大误 · 我烧了单位大楼"},{"title":"琼瑶笔下那么多绝色美女，为什么只有小燕子一夜爆红？","ga_prefix":"122310","images":["https://pic2.zhimg.com/v2-dbbd0d15e0940a1d0bae0edb7da8a315.jpg"],"multipic":true,"type":0,"id":9704161},{"title":"2018 年度「科学崩坏」事件，猜猜是哪个？（是的就是它）","ga_prefix":"122309","images":["https://pic2.zhimg.com/v2-9419fa0941bd23b9f4654e1f09cd0365.jpg"],"multipic":true,"type":0,"id":9704176},{"images":["https://pic4.zhimg.com/v2-4c9527e16460064d0ae1879585be1537.jpg"],"type":0,"id":9704041,"ga_prefix":"122308","title":"日本 90 年代那场消费降级"},{"images":["https://pic2.zhimg.com/v2-c9a81bc4b92b2384deb24e19f644f1a9.jpg"],"type":0,"id":9704126,"ga_prefix":"122307","title":"你是否还记得，2018 年发生过这些搞笑的游戏新闻"},{"images":["https://pic2.zhimg.com/v2-f687212d7178c5c6612e7d00f61faf69.jpg"],"type":0,"id":9704196,"ga_prefix":"122306","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-956c3fcd2461be24daac143a6fd4fd3b.jpg","type":0,"id":9704216,"ga_prefix":"122221","title":"这部片子告诉你，什么是前所未有的，IMAX 的「烂」"},{"image":"https://pic3.zhimg.com/v2-10d53e266589829f2bf04179548e10ca.jpg","type":0,"id":9704126,"ga_prefix":"122307","title":"你是否还记得，2018 年发生过这些搞笑的游戏新闻"},{"image":"https://pic4.zhimg.com/v2-bed3dc0b0a661fb7dfc7c8ff702995a3.jpg","type":0,"id":9704194,"ga_prefix":"122213","title":"请注意，刘强东并非「无罪」，而是「没有被起诉」"},{"image":"https://pic4.zhimg.com/v2-982287451745c41b025bb004569bbc73.jpg","type":0,"id":9704184,"ga_prefix":"122207","title":"不再「浓眉大眼」的微信"},{"image":"https://pic2.zhimg.com/v2-079785497409469bd396be909ad30ec5.jpg","type":0,"id":9704059,"ga_prefix":"122118","title":"看《蜘蛛侠》前先读这篇，你会更好地欣赏这部年度佳作"}]
     */

    var date: String? = null
    var stories: List<StoriesBean>? = null
    var top_stories: List<TopStoriesBean>? = null

    class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-9bf376382c632d7c928d911b43aa46f4.jpg"]
         * type : 0
         * id : 9704221
         * ga_prefix : 122316
         * title : 不会用「丹田气」，也不敢唱调太高的的歌，怎么办？
         * multipic : true
         */

        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
        var isMultipic: Boolean = false
        var images: List<String>? = null
    }

    class TopStoriesBean {
        /**
         * image : https://pic4.zhimg.com/v2-956c3fcd2461be24daac143a6fd4fd3b.jpg
         * type : 0
         * id : 9704216
         * ga_prefix : 122221
         * title : 这部片子告诉你，什么是前所未有的，IMAX 的「烂」
         */

        var image: String? = null
        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
    }

}
