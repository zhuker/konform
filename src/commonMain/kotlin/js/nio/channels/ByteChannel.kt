/*
 * Copyright (c) 2000, 2001, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package js.nio.channels

import js.io.IOException


/**
 * A channel that can read and write bytes.  This interface simply unifies
 * [ReadableByteChannel] and [WritableByteChannel]; it does not
 * specify any new operations.
 *
 * @author Mark Reinhold
 * @author JSR-51 Expert Group
 * @since 1.4
 */

interface ByteChannel : ReadableByteChannel, WritableByteChannel
