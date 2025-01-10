# TheMovie

TheMovie, Android platformunda popüler filmleri keşfetmek ve detaylarını görüntülemek için geliştirilmiş bir uygulamadır. Modern Android teknolojileri kullanılarak inşa edilmiştir.

## Özellikler

- **Popüler Filmler:** Güncel popüler filmlerin bir listesini görüntüleyin.
- **Film Detayları:** Seçilen bir film hakkında detaylı bilgiyi görüntüleyin.

## Kullanılan Teknolojiler

### Android
- **Kotlin:** Uygulamanın geliştirilmesinde kullanılan programlama dili.
- **MVVM Mimari:** Kodun daha modüler, test edilebilir ve sürdürülebilir olmasını sağlamak için kullanılan tasarım deseni.
- **Hilt:** Dependency Injection işlemleri için.
- **Retrofit:** API isteklerini gerçekleştirmek için.
- **RxJava & RxAndroid:** Reaktif programlama için.
- **Gson:** JSON veri dönüşümleri için.
- **Glide:** Görselleri asenkron olarak yüklemek ve önbelleğe almak için.
- **DotsIndicator:** ViewPager göstergeleri için.

### Android Jetpack Kütüphaneleri
- **Navigation Component:** Fragmentler arasında kolay ve güvenli gezinme için.
- **RecyclerView:** Liste yapısını oluşturmak için.
- **SwipeRefreshLayout:** Kullanıcıların sayfayı yenilemesine olanak tanır.
- **ConstraintLayout:** Karmaşık UI düzenleri için.

### UI
- **Material Design:** Kullanıcı dostu ve modern bir arayüz için.

## Kurulum

### Gereksinimler
- Android Studio Arctic Fox veya üzeri
- Minimum SDK: 21
- Önerilen SDK: 33

### Kurulum Adımları
1. **Proje Kopyalama:**
   ```bash
   git clone https://github.com/doseyenc/TheMovie.git
   ```
2. **Bağımlılıkların Yüklenmesi:**
   Proje klasörüne gidin ve aşağıdaki komutu çalıştırın:
   ```bash
   ./gradlew build
   ```
3. **API Anahtarı Ekleme:**
   - `TMDB` API anahtarınızı almak için [The Movie Database](https://www.themoviedb.org/) web sitesine gidin.
   - `local.properties` dosyasına şu satırı ekleyin:
     ```
     TMDB_API_KEY=YOUR_API_KEY
     ```

## Kullanılan Bağımlılıklar

- **Hilt:** `com.google.dagger:hilt-android:${hilt}`
- **Retrofit:** `com.squareup.retrofit2:retrofit:${retrofit}`
- **Gson:** `com.google.code.gson:gson:${gson}`
- **Glide:** `com.github.bumptech.glide:glide:${glide}`
- **RxJava3:** `io.reactivex.rxjava3:rxjava:${rxjava}`
- **RxAndroid:** `io.reactivex.rxjava3:rxandroid:${rxandroid}`
- **DotsIndicator:** `com.tbuonomo:dotsindicator:${dotsindicator}`
- **Navigation Component:** `androidx.navigation:navigation-fragment-ktx:${navigationFragmentKtx}`

---

Herhangi bir sorun veya öneriniz varsa lütfen benimle iletişime geçin!
