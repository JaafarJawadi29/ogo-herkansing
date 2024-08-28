
/**
 * <p>An Accessor makes it possible to read and write data
 * for a presentation.</p>
 * <p>Non-abstract subclasses should implement the load and save methods.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class AccessorFactory
{
	public static Reader getReader(String filename)
	{
		if (filename.endsWith(Reader.DEFAULT_EXTENSION))
		{
			return new XMLReader();
		}
		else
		{
			throw new IllegalArgumentException("File type unknown. Filename: " + filename);
		}
	}

	public static Reader getReaderWithArgs(String[] arguments)
	{
		if (arguments.length == 0)
		{
			return new DemoPresentationReader();
		}
		else
		{
			return new XMLReader();
		}
	}

	public static Writer getWriter(String filename)
	{
		if (filename.endsWith(Writer.DEFAULT_EXTENSION))
		{
			return new XMLWriter();
		}
		else
		{
			throw new IllegalArgumentException("File type unknown. Filename: " + filename);
		}
	}
}
