package cn.xin.mvp.demo.bean

import cn.xin.lib.net.HttpResult

/**
 * @author Jianxin n.zjx@163.com
 * @date 2019-08-31 031 下午 08:25
 * SmallMVP cn.xin.mvp.demo.bean
 */
data class SubjectBean(
    val aka: List<String> = listOf(),
    val alt: String = "",
    val blooper_urls: List<String> = listOf(),
    val bloopers: List<Blooper> = listOf(),
    val casts: List<Cast> = listOf(),
    val clip_urls: List<String> = listOf(),
    val clips: List<Clip> = listOf(),
    val collect_count: Int = 0,
    val collection: Any = Any(),
    val comments_count: Int = 0,
    val countries: List<String> = listOf(),
    val current_season: Any = Any(),
    val directors: List<Director> = listOf(),
    val do_count: Any = Any(),
    val douban_site: String = "",
    val durations: List<String> = listOf(),
    val episodes_count: Any = Any(),
    val genres: List<String> = listOf(),
    val has_schedule: Boolean = false,
    val has_ticket: Boolean = false,
    val has_video: Boolean = false,
    val id: String = "",
    val images: Images = Images(),
    val languages: List<String> = listOf(),
    val mainland_pubdate: String = "",
    val mobile_url: String = "",
    val original_title: String = "",
    val photos: List<Photo> = listOf(),
    val photos_count: Int = 0,
    val popular_comments: List<PopularComment> = listOf(),
    val popular_reviews: List<PopularReview> = listOf(),
    val pubdate: String = "",
    val pubdates: List<String> = listOf(),
    val rating: Rating = Rating(),
    val ratings_count: Int = 0,
    val reviews_count: Int = 0,
    val schedule_url: String = "",
    val seasons_count: Any = Any(),
    val share_url: String = "",
    val subtype: String = "",
    val summary: String = "",
    val tags: List<String> = listOf(),
    val title: String = "",
    val trailer_urls: List<String> = listOf(),
    val trailers: List<Trailer> = listOf(),
    val videos: List<Video> = listOf(),
    val website: String = "",
    val wish_count: Int = 0,
    val writers: List<Writer> = listOf(),
    val year: String = ""
) : HttpResult() {

}

data class Cast(
    val alt: String = "",
    val avatars: AvatarsX = AvatarsX(),
    val id: String = "",
    val name: String = "",
    val name_en: String = ""
)

data class Avatars(
    val large: String = "",
    val medium: String = "",
    val small: String = ""
)

data class Rating(
    val average: Double = 0.0,
    val details: Map<String, Int> = mapOf(),
    val max: Int = 0,
    val min: Int = 0,
    val stars: String = ""
)

data class Clip(
    val alt: String = "",
    val id: String = "",
    val medium: String = "",
    val resource_url: String = "",
    val small: String = "",
    val subject_id: String = "",
    val title: String = ""
)

data class Photo(
    val alt: String = "",
    val cover: String = "",
    val icon: String = "",
    val id: String = "",
    val image: String = "",
    val thumb: String = ""
)

data class Director(
    val alt: String = "",
    val avatars: AvatarsXX = AvatarsXX(),
    val id: String = "",
    val name: String = "",
    val name_en: String = ""
)

data class AvatarsX(
    val large: String = "",
    val medium: String = "",
    val small: String = ""
)

data class Writer(
    val alt: String = "",
    val avatars: Avatars = Avatars(),
    val id: String = "",
    val name: String = "",
    val name_en: String = ""
)

data class AvatarsXX(
    val large: String = "",
    val medium: String = "",
    val small: String = ""
)

data class PopularComment(
    val author: Author = Author(),
    val content: String = "",
    val created_at: String = "",
    val id: String = "",
    val rating: RatingX = RatingX(),
    val subject_id: String = "",
    val useful_count: Int = 0
)

data class RatingX(
    val max: Int = 0,
    val min: Int = 0,
    val value: Double = 0.0
)

data class Author(
    val alt: String = "",
    val avatar: String = "",
    val id: String = "",
    val name: String = "",
    val signature: String = "",
    val uid: String = ""
)

data class PopularReview(
    val alt: String = "",
    val author: AuthorX = AuthorX(),
    val id: String = "",
    val rating: RatingXX = RatingXX(),
    val subject_id: String = "",
    val summary: String = "",
    val title: String = ""
)

data class AuthorX(
    val alt: String = "",
    val avatar: String = "",
    val id: String = "",
    val name: String = "",
    val signature: String = "",
    val uid: String = ""
)

data class RatingXX(
    val max: Int = 0,
    val min: Int = 0,
    val value: Double = 0.0
)

data class Blooper(
    val alt: String = "",
    val id: String = "",
    val medium: String = "",
    val resource_url: String = "",
    val small: String = "",
    val subject_id: String = "",
    val title: String = ""
)

data class Images(
    val large: String = "",
    val medium: String = "",
    val small: String = ""
)

data class Video(
    val need_pay: Boolean = false,
    val sample_link: String = "",
    val source: Source = Source(),
    val video_id: String = ""
)

data class Source(
    val literal: String = "",
    val name: String = "",
    val pic: String = ""
)

data class Trailer(
    val alt: String = "",
    val id: String = "",
    val medium: String = "",
    val resource_url: String = "",
    val small: String = "",
    val subject_id: String = "",
    val title: String = ""
)