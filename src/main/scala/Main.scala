//  Created by Selman Karaosmanoglu on 11.01.2019.
//  Karaosmanoglu is a Master's Student at the Informatics Institute of Afyon Kocatepe University
//  Copyright © 2019 Selman Karaosmanoglu. All rights reserved.

//  Test System:
//    macOS Mojave(10.14.2) running on MacBook Pro(Early 2015)
//    java version "1.8.0_181"
//    Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
//    Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
//    Scala Version 2.12.8
//    SBT version 1.0


import com.github.tototoshi.csv._
import java.io.File

object Main extends App {
  val arastirmaci = CSVReader.open(new File("src/resources/verigiris1.csv")).all().flatten
  val kodlayicibir = CSVReader.open(new File("src/resources/verigiris2.csv")).all().flatten
  val kodlayiciki = CSVReader.open(new File("src/resources/verigiris3.csv")).all().flatten

  //Arastirmaci ile birinci kodlayici arasindaki farklarin toplami
  val diff1 = arastirmaci.zip(kodlayicibir).filter(x => x._1 != x._2).size
  //Arastirmaci ile ikinci kodlayici arasindaki farklarin toplami
  val diff2 = arastirmaci.zip(kodlayiciki).filter(x => x._1 != x._2).size
  //Arastirmaci ve kodlayicilarin arasindaki farklarin toplami(ucunun ayni olmadigi durumlar)
  val diff3 = arastirmaci.zip(kodlayicibir).zip(kodlayiciki).map(x=> (x._1._1,x._1._2,x._2)).filter(x => x._1 != x._2 || x._2 != x._3 || x._1 != x._3).size
  println(diff1) //171
  println(diff2) //128
  println(diff3) //236

  //GUVENILIRLIK KATSAYILARI
  //Arastirmaci ve birinci kodlayici arasindaki
  val r1 = (2f * (15000f - diff1)) / 30000f
  //Arastirmaci ve ikinci kodlayici arasindaki
  val r2 = (2f * (15000f - diff2)) / 30000f
  println(r1) //0.9886
  println(r2) //0.99146664
  //Ucunun arasindaki
  val r3 = (3f * (15000f - diff3)) / 45000f
  println(r3) //0.98426664
}