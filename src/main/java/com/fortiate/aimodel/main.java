package com.fortiate.aimodel;

import java.io.*;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.prediction.*;

import com.fortiate.aimodel.FortiateGBMPojo;

public class main {
  private static String modelClassName = "com.fortiate.aimodel.FortiateGBMPojo";

  public static void main(String[] args) throws Exception {
    hex.genmodel.GenModel rawModel;
    rawModel = (hex.genmodel.GenModel) Class.forName(modelClassName).newInstance();
    EasyPredictModelWrapper model = new EasyPredictModelWrapper(rawModel);
    //
    // By default, unknown categorical levels throw PredictUnknownCategoricalLevelException.
    // Optionally configure the wrapper to treat unknown categorical levels as N/A instead
    // and strings that cannot be converted to numbers also to N/As:
    //
    //     EasyPredictModelWrapper model = new EasyPredictModelWrapper(
    //         new EasyPredictModelWrapper.Config()
    //             .setModel(rawModel)
    //             .setConvertUnknownCategoricalLevelsToNa(true)
    //             .setConvertInvalidNumbersToNa(true)
    //     );

    RowData row = new RowData();
     row.put("Year", "1987");
     row.put("Month", "10");
     row.put("DayofMonth", "14");
     row.put("DayOfWeek", "3");
     row.put("CRSDepTime", "730");
     row.put("UniqueCarrier", "PS");
     row.put("Origin", "SAN");
     row.put("Dest", "SFO");

    BinomialModelPrediction p = model.predictBinomial(row);
    System.out.println("Label (aka prediction) is flight departure delayed: " + p.label);
    System.out.print("Class probabilities: ");
    for (int i = 0; i < p.classProbabilities.length; i++) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(p.classProbabilities[i]);
    }
    System.out.println("");
  }
}