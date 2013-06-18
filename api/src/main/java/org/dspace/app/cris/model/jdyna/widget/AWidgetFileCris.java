package org.dspace.app.cris.model.jdyna.widget;

import it.cilea.osd.jdyna.util.ValidationMessage;
import it.cilea.osd.jdyna.value.FileValue;
import it.cilea.osd.jdyna.widget.WidgetFile;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AWidgetFileCris extends WidgetFile
{

    private boolean useInStatistics; 
    
    @Override
    public FileValue getInstanceValore() {
        return new FileValue();
    }
    
    @Override
    public Class<FileValue> getValoreClass() {      
        return FileValue.class;
    }

    @Override
    public ValidationMessage valida(Object valore) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setUseInStatistics(boolean useInStatistics)
    {
        this.useInStatistics = useInStatistics;
    }

    public boolean isUseInStatistics()
    {
        return useInStatistics;
    }


}
