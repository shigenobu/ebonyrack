package com.walksocket.er.sqlite.context;

import com.walksocket.er.Copiable;
import com.walksocket.er.Date;
import com.walksocket.er.Json;
import com.walksocket.er.Utils;
import com.walksocket.er.Value;
import com.walksocket.er.sqlite.entity.DbSequence;
import com.walksocket.er.sqlite.entity.DbSequenceOption;

/**
 * CtxSequence.
 */
public class CtxSequence implements Value, Copiable<CtxSequence> {

  /**
   * dbSequence.
   */
  public DbSequence dbSequence;

  /**
   * dbSequenceOption.
   */
  public DbSequenceOption dbSequenceOption;

  @Override
  public CtxSequence copy() {
    var newSequenceId = Utils.randomString();
    var newCtxSequence = Json.copy(this, CtxSequence.class);

    // DbSequence
    newCtxSequence.dbSequence.sequenceId = newSequenceId;
    newCtxSequence.dbSequence.sequenceName = String.format("CopyOf_%s_%s",
        newCtxSequence.dbSequence.sequenceName,
        Date.timestamp());

    // DbSequenceOption
    newCtxSequence.dbSequenceOption.sequenceId = newSequenceId;

    return newCtxSequence;
  }
}
