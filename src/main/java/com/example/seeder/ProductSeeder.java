package com.example.seeder;

import com.example.entity.Product;
import com.example.repository.ProductSeederepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {

    private final ProductSeederepository productRepository;

    public ProductSeeder(ProductSeederepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) {
            System.out.println("ℹ️ Producteeder: Data already exists, skipping seeding.");
            return;
        }

        List<Product> Product = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        Product.add(new Product(null, "Samsung Galaxy S24 Ultra", "Smartphone flagship dengan kamera 200MP dan Snapdragon 8 Gen 3.", 20999000L, now, now));
        Product.add(new Product(null, "iPhone 15 Pro Max", "iPhone terbaru dengan chip A17 Pro dan material titanium.", 23999000L, now, now));
        Product.add(new Product(null, "Xiaomi 14 Ultra", "Fotografi terbaik dengan Leica lens dan Snapdragon 8 Gen 3.", 15999000L, now, now));
        Product.add(new Product(null, "OPPO Find X7 Ultra", "Flagship OPPO dengan dual periscope telephoto dan Hasselblad tuning.", 17999000L, now, now));
        Product.add(new Product(null, "Vivo X100 Pro", "HP flagship Vivo dengan ZEISS optics dan Dimensity 9300.", 14999000L, now, now));
        Product.add(new Product(null, "Asus ROG Phone 8 Pro", "HP gaming terbaik dengan AirTrigger dan Snapdragon 8 Gen 3.", 16999000L, now, now));
        Product.add(new Product(null, "Realme GT5 Pro", "Performa flagship dengan harga kompetitif, Snapdragon 8 Gen 3.", 10999000L, now, now));
        Product.add(new Product(null, "Google Pixel 8 Pro", "Android murni dengan AI kamera terbaik dari Google.", 18999000L, now, now));
        Product.add(new Product(null, "Infinix Zero 30 5G", "HP murah dengan kamera selfie 50MP dan desain tipis.", 3599000L, now, now));
        Product.add(new Product(null, "POCO F5 Pro", "Performa tinggi dengan harga terjangkau, layar AMOLED 120Hz.", 5999000L, now, now));
        Product.add(new Product(null, "Tecno Phantom V Flip", "HP lipat termurah di Indonesia, layar fleksibel 6.9 inci.", 8499000L, now, now));
        Product.add(new Product(null, "Nokia G42 5G", "Smartphone Nokia dengan Android murni dan build kokoh.", 3299000L, now, now));
        Product.add(new Product(null, "Sony Xperia 1 V", "Flagship Sony dengan layar 4K OLED dan teknologi Alpha Camera.", 20999000L, now, now));
        Product.add(new Product(null, "Motorola Edge 50 Pro", "Desain menarik, Snapdragon 7 Gen 3 dan pengisian cepat 125W.", 7499000L, now, now));
        Product.add(new Product(null, "Honor Magic6 Pro", "Desain mewah, kamera 180MP dan AI eye-tracking.", 19999000L, now, now));
        Product.add(new Product(null, "Redmi Note 13 Pro+", "Mid-range killer dengan 200MP kamera dan IP68 rating.", 5799000L, now, now));
        Product.add(new Product(null, "OPPO A98 5G", "HP kelas menengah dengan RAM besar dan pengisian cepat.", 4299000L, now, now));
        Product.add(new Product(null, "Vivo V30 Pro", "Desain tipis, kamera ZEISS, dan pengisian 80W.", 6599000L, now, now));
        Product.add(new Product(null, "Samsung Galaxy A55 5G", "Midrange Samsung terbaru dengan Exynos 1480.", 6299000L, now, now));
        Product.add(new Product(null, "iQOO Z9 Turbo", "Snapdragon 8s Gen 3, 144Hz AMOLED, dan harga miring.", 5999000L, now, now));
        Product.add(new Product(null, "OnePlus 12R", "Performa flagship dengan OxygenOS dan Snapdragon 8 Gen 2.", 8999000L, now, now));
        Product.add(new Product(null, "Realme Narzo 70 Pro", "HP gaming budget dengan MediaTek Dimensity 7050.", 3799000L, now, now));
        Product.add(new Product(null, "Asus Zenfone 10", "Kecil tapi bertenaga, flagship compact dengan Snapdragon 8 Gen 2.", 10999000L, now, now));
        Product.add(new Product(null, "POCO X6 Pro", "Dimensity 8300 Ultra dan layar AMOLED 120Hz.", 4599000L, now, now));
        Product.add(new Product(null, "Redmi K70 Pro", "Performa gahar dengan Snapdragon 8 Gen 3 dan harga kompetitif.", 7899000L, now, now));
        Product.add(new Product(null, "Samsung Galaxy Z Flip5", "Smartphone lipat stylish, lebih kokoh dan layar cover besar.", 15499000L, now, now));
        Product.add(new Product(null, "OPPO Reno11 Pro", "Kamera portrait unggulan dan desain lengkung elegan.", 8499000L, now, now));
        Product.add(new Product(null, "Xiaomi Pad 6S Pro", "Tablet flagship Xiaomi dengan layar 144Hz dan stylus support.", 9999000L, now, now));
        Product.add(new Product(null, "Lenovo Legion Go", "Console gaming Android dengan layar 8.8” dan detachable controller.", 12999000L, now, now));
        Product.add(new Product(null, "Nothing Phone (2a)", "Desain unik transparan dan performa solid di kelasnya.", 5999000L, now, now));

        productRepository.saveAll(Product);
        System.out.println("✅ Producteeder: 30 new gadget successfully insert");
    }
}
