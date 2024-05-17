package com.example.dummyjsonapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dummyjsonapp.entity.Category
import com.example.dummyjsonapp.entity.Product

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + CATEGORY_TABLE_NAME + " (" +
                ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT" + ")")
        db.execSQL(query)
        val productQuery = ("CREATE TABLE " + PRODUCT_TABLE_NAME + " (" +
                ID_COL + " INTEGER PRIMARY KEY, " +
                TITLE_COl + " TEXT," +
                DESCRIPTION_COl + " TEXT,"  +
                PRICE_COl + " INTEGER,"  +
                DISCOUNT_PERCENTAGE_COl + " REAL,"  +
                RATING_COl + " REAL,"  +
                STOCK_COl + " INTEGER,"  +
                BRAND_COl + " TEXT,"  +
                CATEGORY_COl + " TEXT,"  +
                THUMBNAIL_COl + " TEXT"  + ")")
        db.execSQL(productQuery)
        val productImagesQuery = ("CREATE TABLE " + PRODUCTIMAGES_TABLE_NAME + " (" +
                ID_COL + " INTEGER PRIMARY KEY, " +
                IMAGE_PATH_COl + " TEXT, " +
                PRODUCT_ID_COl + " INTEGER, " +
                " FOREIGN KEY (" + PRODUCT_ID_COl + ") REFERENCES " + PRODUCT_TABLE_NAME + " ( " + ID_COL + " ));")
        db.execSQL(productImagesQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME)
        onCreate(db)
    }

    fun addCategory(name : String){

        if (getCategoryList(name).isNotEmpty()) {
            return
        }
        val values = ContentValues()
        values.put(NAME_COl, name)
        val db = this.writableDatabase
        db.insert(CATEGORY_TABLE_NAME, null, values)
        db.close()
    }

    fun getCategory(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $CATEGORY_TABLE_NAME", null)
    }

    fun getCategory(name: String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $CATEGORY_TABLE_NAME WHERE name = ?", arrayOf(name))
    }

    @SuppressLint("Range")
    fun getCategoryList(): ArrayList<Category> {
        val categoryList: ArrayList<Category> = ArrayList()
        val cursor = getCategory()
        if (cursor!!.moveToFirst()) {
            do {
                val category = Category("")
                category.name = cursor.getString(cursor.getColumnIndex(NAME_COl))
                categoryList.add(category)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categoryList
    }

    @SuppressLint("Range")
    fun getCategoryList(name: String): ArrayList<Category> {
        var categoryList: ArrayList<Category> = ArrayList()
        val cursor = getCategory(name)
        if (cursor!!.moveToFirst()) {
            do {
                val category = Category("")
                category.name = cursor.getString(cursor.getColumnIndex(NAME_COl))
                categoryList.add(category)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return categoryList
    }

    fun getProduct(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $PRODUCT_TABLE_NAME", null)
    }

    fun getProduct(productId: Int): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $PRODUCT_TABLE_NAME WHERE id = ?", arrayOf(productId.toString()))
    }

    fun addProduct(product: Product){

        if (getProductList(product.id!!).isNotEmpty()) {
            return
        }
        val values = ContentValues()
        values.put(ID_COL, product.id)
        values.put(TITLE_COl, product.title)
        values.put(DESCRIPTION_COl, product.description)
        values.put(PRICE_COl, product.price)
        values.put(DISCOUNT_PERCENTAGE_COl, product.discountPercentage)
        values.put(RATING_COl, product.rating)
        values.put(STOCK_COl, product.stock)
        values.put(BRAND_COl, product.brand)
        values.put(CATEGORY_COl, product.category)
        values.put(THUMBNAIL_COl, product.thumbnail)
        val db = this.writableDatabase
        db.insert(PRODUCT_TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getProductList(): ArrayList<Product> {
        val productList: ArrayList<Product> = ArrayList()
        val cursor = getProduct()
        if (cursor!!.moveToFirst()) {
            do {
                val product = Product(
                    cursor.getInt(cursor.getColumnIndex(ID_COL)),
                    cursor.getString(cursor.getColumnIndex(TITLE_COl)),
                    cursor.getString(cursor.getColumnIndex(DESCRIPTION_COl)),
                    cursor.getInt(cursor.getColumnIndex(PRICE_COl)),
                    cursor.getDouble(cursor.getColumnIndex(DISCOUNT_PERCENTAGE_COl)),
                    cursor.getDouble(cursor.getColumnIndex(RATING_COl)),
                    cursor.getInt(cursor.getColumnIndex(STOCK_COl)),
                    cursor.getString(cursor.getColumnIndex(BRAND_COl)),
                    cursor.getString(cursor.getColumnIndex(CATEGORY_COl)),
                    cursor.getString(cursor.getColumnIndex(THUMBNAIL_COl)),
                    emptyList(), // TODO product images
                )
                productList.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productList
    }

    @SuppressLint("Range")
    fun getProductList(productId: Int): ArrayList<Product> {
        val productList: ArrayList<Product> = ArrayList()
        val cursor = getProduct(productId)
        if (cursor!!.moveToFirst()) {
            do {
                val product = Product(
                    cursor.getInt(cursor.getColumnIndex(ID_COL)),
                    cursor.getString(cursor.getColumnIndex(TITLE_COl)),
                    cursor.getString(cursor.getColumnIndex(DESCRIPTION_COl)),
                    cursor.getInt(cursor.getColumnIndex(PRICE_COl)),
                    cursor.getDouble(cursor.getColumnIndex(DISCOUNT_PERCENTAGE_COl)),
                    cursor.getDouble(cursor.getColumnIndex(RATING_COl)),
                    cursor.getInt(cursor.getColumnIndex(STOCK_COl)),
                    cursor.getString(cursor.getColumnIndex(BRAND_COl)),
                    cursor.getString(cursor.getColumnIndex(CATEGORY_COl)),
                    cursor.getString(cursor.getColumnIndex(THUMBNAIL_COl)),
                    emptyList(), // TODO product images
                )
                productList.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productList
    }

    companion object{

        private val DATABASE_NAME = "myapp"
        private val DATABASE_VERSION = 1

        val CATEGORY_TABLE_NAME = "category_table"
        val PRODUCT_TABLE_NAME = "product_table"
        val PRODUCTIMAGES_TABLE_NAME = "product_images_table"

        val ID_COL = "id"
        val NAME_COl = "name"
        val TITLE_COl = "title"
        val DESCRIPTION_COl = "description"
        val PRICE_COl = "price"
        val DISCOUNT_PERCENTAGE_COl = "discountPercentage"
        val RATING_COl = "rating"
        val STOCK_COl = "stock"
        val BRAND_COl = "brand"
        val CATEGORY_COl = "category"
        val THUMBNAIL_COl = "thumbnail"
        val PRODUCT_ID_COl = "productId"
        val IMAGE_PATH_COl = "imagePath"
    }
}