/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;

import java.util.Iterator;

/**
 * A list of resources in a {@linkplain ASTTryStatement try-with-resources}.
 *
 * <pre class="grammar">
 *
 * ResourceList ::= "(" {@link ASTResource Resource} ( ";" {@link ASTResource Resource} )* ";"? ")"
 *
 * </pre>
 */
public final class ASTResourceList extends AbstractJavaNode implements Iterable<ASTResource> {

    private boolean trailingSemi;

    ASTResourceList(int id) {
        super(id);
    }

    ASTResourceList(JavaParser p, int id) {
        super(p, id);
    }

    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }

    void setTrailingSemi() {
        this.trailingSemi = true;
    }

    /**
     * Returns true if this resource list has a trailing semicolon, eg
     * in {@code try (InputStream is = getInputStream();) { ... }}.
     */
    public boolean hasTrailingSemiColon() {
        return trailingSemi;
    }

    @Override
    public Iterator<ASTResource> iterator() {
        return new NodeChildrenIterator<>(this, ASTResource.class);
    }
}