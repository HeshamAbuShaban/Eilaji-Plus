package dev.training.eilaji_plus.data.static_factory

import dev.training.eilaji_plus.R
import dev.training.eilaji_plus.data.models.fixed.OnBoardingItem
import dev.training.eilaji_plus.data.models.server.Ad
import dev.training.eilaji_plus.data.models.server.Pharmacy
import dev.training.eilaji_plus.data.models.server.SubCategory

object StaticFactory {

    val onBoardingItems = listOf(
        OnBoardingItem(
            R.drawable.on_boarding_1,
            "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص "
        ),
        OnBoardingItem(
            R.drawable.on_boarding_2,
            "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص "
        ),
        OnBoardingItem(
            R.drawable.on_boarding_3,
            "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص "
        ),
    )

    val listSubCategories = listOf(
        SubCategory(
            "095f3500-6321-4f27-bd33-c39fa9d232b1",
            "E3RPh39EA3NrLaDMRFdg",
            "https://m7et.com/wp-content/uploads/2021/04/%D8%B7%D8%B1%D9%82-%D8%A7%D9%84%D8%B9%D9%86%D8%A7%D9%8A%D8%A9-%D8%A8%D8%A7%D9%84%D8%A7%D8%B3%D9%86%D8%A7%D9%86-%D9%81%D9%8A-%D8%A7%D9%84%D9%85%D9%86%D8%B2%D9%84-780x470.jpg.webp",
            "عيون",
        ),
        SubCategory(
            "1412ef0b-b622-45c8-b30c-a3feaa83494b",
            "E3RPh39EA3NrLaDMRFdg",
            "https://www.afdal.best/wp-content/uploads/2020/10/Teaser-BlueHeaven-CDP-Teaser-3080x806-ar.jpg",
            "مرطبات الجلد",
        ),
        SubCategory(
            "095f3500-6321-4f27-bd33-c39fa9d232b1",
            "E3RPh39EA3NrLaDMRFdg",
            "https://m7et.com/wp-content/uploads/2021/04/%D8%B7%D8%B1%D9%82-%D8%A7%D9%84%D8%B9%D9%86%D8%A7%D9%8A%D8%A9-%D8%A8%D8%A7%D9%84%D8%A7%D8%B3%D9%86%D8%A7%D9%86-%D9%81%D9%8A-%D8%A7%D9%84%D9%85%D9%86%D8%B2%D9%84-780x470.jpg.webp",
            "أسنان",
        ),
        SubCategory(
            "095f3500-6321-4f27-bd33-c39fa9d232b1",
            "https://m7et.com/wp-content/uploads/2021/04/%D8%B7%D8%B1%D9%82-%D8%A7%D9%84%D8%B9%D9%86%D8%A7%D9%8A%D8%A9-%D8%A8%D8%A7%D9%84%D8%A7%D8%B3%D9%86%D8%A7%D9%86-%D9%81%D9%8A-%D8%A7%D9%84%D9%85%D9%86%D8%B2%D9%84-780x470.jpg.webp",
            "E3RPh39EA3NrLaDMRFdg",
            "الحنجرة",
        )
    )

    val listPharmaciesModels = listOf(
        Pharmacy(
            "adfgdfgfdgg7dsf7g6df",
            "https://firebasestorage.googleapis.com/v0/b/eilaji-9b01b.appspot.com/o/v9IN7O0myxRoeKfUuaDdgTrJJ1n1%2FPharmaciesImages%2F359d464b-6ea2-4056-bcfe-37cb62512abb.jpg?alt=media&token=be6a8417-b9c4-4d52-b449-86af68c87a2c",
            "فتيح",
            "+970597152714",
            "الرمال - دوار فتوح - مقابل برج المعادي",
            31.44927529166395,
            34.39462522569722,
            "cHvTb0OtQsWROdx5QxL-oV:APA91bEzVhhR3kw03tsZf1L7vaFVHJR-5OEsFtHJvuMoU7FVTbVfuvVPCnTpth9sCpT41VR2aQv-DiPQ3U8pv-RR0ujV1etY-otlCxDzwPl8yz6zab8nxXtPKxrQTWleLm1lgzbLw-Rf"
        ),
        Pharmacy(
            "adfgdfg345fdg7dsf7g6df",
            "https://firebasestorage.googleapis.com/v0/b/eilaji-9b01b.appspot.com/o/v9IN7O0myxRoeKfUuaDdgTrJJ1n1%2FPharmaciesImages%2F359d464b-6ea2-4056-bcfe-37cb62512abb.jpg?alt=media&token=be6a8417-b9c4-4d52-b449-86af68c87a2c",
            "تقى عابدين",
            "+970568856720",
            "الشجاعية - مقابل برج وطن",
            31.450573262978924,
            34.393475898066065,
            "cJ7SP3IeSnagT4EczukGPm:APA91bG6vpAyuoXDQDLLqnTiWyXc3Lw0z2oiXvP1XD6FN4pttQXvkQq70qrui4umlY8BfyM_1aq74pykevcGTLWthTFQKroq2KUHgKiXJG0uZ58oN2_w_LyzOq3bHU_ZeTisUImafUmT"
        ),
        Pharmacy(
            "adfgdfgfdg7dsf34t7g6df",
            "https://firebasestorage.googleapis.com/v0/b/eilaji-9b01b.appspot.com/o/v9IN7O0myxRoeKfUuaDdgTrJJ1n1%2FPharmaciesImages%2F359d464b-6ea2-4056-bcfe-37cb62512abb.jpg?alt=media&token=be6a8417-b9c4-4d52-b449-86af68c87a2c",
            "عبق الشجر",
            "+970562005006",
            "الشجاعية - بجوار مسجد الصحابة",
            31.448934162124537,
            34.39408320427861,
            "cJ7SP3IeSnagT4EczukGPm:APA91bG6vpAyuoXDQDLLqnTiWyXc3Lw0z2oiXvP1XD6FN4pttQXvkQq70qrui4umlY8BfyM_1aq74pykevcGTLWthTFQKroq2KUHgKiXJG0uZ58oN2_w_LyzOq3bHU_ZeTisUImafUmT"
        ),
        Pharmacy(
            "adfgdfgfdg7dsf7g6df",
            "https://firebasestorage.googleapis.com/v0/b/eilaji-9b01b.appspot.com/o/v9IN7O0myxRoeKfUuaDdgTrJJ1n1%2FPharmaciesImages%2F359d464b-6ea2-4056-bcfe-37cb62512abb.jpg?alt=media&token=be6a8417-b9c4-4d52-b449-86af68c87a2c",
            "عبد العزيز محمود حبيب",
            "+970598756400",
            "النصيرات - بجوار ابو دلال مول",
            31.44747884783767,
            34.392811840775806,
            "cJ7SP3IeSnagT4EczukGPm:APA91bG6vpAyuoXDQDLLqnTiWyXc3Lw0z2oiXvP1XD6FN4pttQXvkQq70qrui4umlY8BfyM_1aq74pykevcGTLWthTFQKroq2KUHgKiXJG0uZ58oN2_w_LyzOq3bHU_ZeTisUImafUmT"
        ),
        Pharmacy(
            "adfgdfgfdg7d34tsf7g6df",
            "https://firebasestorage.googleapis.com/v0/b/eilaji-9b01b.appspot.com/o/v9IN7O0myxRoeKfUuaDdgTrJJ1n1%2FPharmaciesImages%2F359d464b-6ea2-4056-bcfe-37cb62512abb.jpg?alt=media&token=be6a8417-b9c4-4d52-b449-86af68c87a2c",
            "الرمادي",
            "+9705993236841",
            "النصيرات - السوق - منتصف شارع القسام",
            31.447918798916653,
            34.39636771288554,
            "cJ7SP3IeSnagT4EczukGPm:APA91bG6vpAyuoXDQDLLqnTiWyXc3Lw0z2oiXvP1XD6FN4pttQXvkQq70qrui4umlY8BfyM_1aq74pykevcGTLWthTFQKroq2KUHgKiXJG0uZ58oN2_w_LyzOq3bHU_ZeTisUImafUmT"
        )
    )

    val ads = listOf(
        Ad(
            "1",
            "https://www.alldaychemist.com/pub/media/catalog/product/cache/ce44d1d7776a58df660c373780297695/t/r/trifed-tablets-india_1_1.png",
            "Trifed"
        ),
        Ad(
            "2",
            "https://media.istockphoto.com/id/1097992544/photo/aspirin-pill-on-a-white-background.jpg?s=612x612&w=0&k=20&c=rYlWqL4_6n1jFv0_wWp0-0Z_39d8Vq9hU-2iI3rQvU8=",
            "Aspirin"
        ),
        Ad(
            "3",
            "https://cdn.shopify.com/s/files/1/0666/7943/products/481328_1_400x.jpg?v=1675718347",
            "Acamol"
        ),
        Ad(
            "4",
            "https://www.p514.com/50519-large_default/%D9%85%D8%B3%D9%83%D9%91%D9%86%D8%A7%D8%AA-%D8%A8%D8%A7%D8%B1%D8%A7%D8%B3%D9%8A%D8%AA%D8%A7%D9%85%D9%88%D9%84-500-%D9%85%D8%BA.jpg",
            "paracetamol"
        ),
        Ad(
            "5",
            "",
            "Trofin"
        ),
        Ad(
            "6",
            "",
            "Nurofen"
        ),
        Ad(
            "7",
            "",
            "Mefenamic"
        ),
        Ad(
            "8",
            "https://www.p514.com/50519-large_default/%D9%85%D8%B3%D9%83%D9%",
            "Nexuim"
        )
    )
}