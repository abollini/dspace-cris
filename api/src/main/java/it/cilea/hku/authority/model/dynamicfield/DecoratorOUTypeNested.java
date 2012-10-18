package it.cilea.hku.authority.model.dynamicfield;

import it.cilea.osd.jdyna.model.ADecoratorTypeDefinition;
import it.cilea.osd.jdyna.model.IContainable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@NamedQueries( {
    @NamedQuery(name = "DecoratorOUTypeNested.findAll", query = "from DecoratorOUTypeNested order by id"),
    @NamedQuery(name = "DecoratorOUTypeNested.uniqueContainableByDecorable", query = "from DecoratorOUTypeNested where real.id = ?"),
    @NamedQuery(name = "DecoratorOUTypeNested.uniqueContainableByShortName", query = "from DecoratorOUTypeNested where real.shortName = ?")
    
})
@DiscriminatorValue(value="typeounestedobject")
public class DecoratorOUTypeNested extends
    ADecoratorTypeDefinition<OUTypeNestedObject, OUNestedPropertiesDefinition>
{

    @OneToOne(optional=true)
    @JoinColumn(name="typeounestedobject_fk")
    @Cascade(value = {CascadeType.ALL,CascadeType.DELETE_ORPHAN})
    private OUTypeNestedObject real;

    @Override
    public String getShortName()
    {        
        return this.real.getShortName();
    }

    @Override
    public Integer getAccessLevel()
    {
        return this.real.getAccessLevel();
    }

    @Override
    public String getLabel()
    {
        return this.real.getLabel();
    }

    @Override
    public boolean getRepeatable()
    {
        return this.real.isRepeatable();
    }

    @Override
    public int getPriority()
    {
        return this.real.getPriority();
    }

    @Override
    public int compareTo(IContainable o) {
        OUTypeNestedObject oo = null;
        if(o instanceof DecoratorOUTypeNested) {
            oo = (OUTypeNestedObject)o.getObject();
            return this.real.compareTo(oo);
        }
        return 0;
    }
    
    @Override
    public void setReal(OUTypeNestedObject object)
    {
       this.real = object;        
    }

    @Override
    public OUTypeNestedObject getObject()
    {
        return real;
    }

    public boolean isNewline() {
        return real.isNewline();
    }
    
}
